package com.br.auth.controller;
import com.br.auth.Service.AuthService;
import com.br.auth.model.User;
import com.br.auth.security.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping(value = "api/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTConfig jwtConfig;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping(value = "portService")
    public ResponseEntity checkPortService(){
        return ResponseEntity.ok("Auth running in " + serverPort);
    }

    record Login(String username, String password){}
    @PostMapping("/token-login")
    public ResponseEntity<String> generateToken(@RequestBody  Login login){
        ResponseEntity response;
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(login.username(), login.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        var user = (User) authentication.getPrincipal();
        String token = jwtConfig.generateTokenAcess(user);

        if (Objects.isNull(token))
        {
            response = new ResponseEntity<>("não foi possível realizar login!", HttpStatus.BAD_REQUEST);
        }else{
            response = new ResponseEntity<>(token, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User.UserRecord user){
        ResponseEntity response;
        User userResult = service.Save(user);
        if(!Objects.isNull(userResult)){
            response = new ResponseEntity<>(userResult, HttpStatus.OK);
        }else{
            response = new ResponseEntity<>("erro ao tentar salvar usuário", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<User.UserRecord> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id){
        ResponseEntity response;
        User.UserRecord responseUser = service.findById(id);
        if(!Objects.isNull(responseUser)){
            response = new ResponseEntity<>(responseUser, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar usuário com o id informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping(value = "/user/{login}")
    public ResponseEntity<?> findByLogin(@PathVariable String login){
        ResponseEntity response;
        User.UserRecord responseUser = service.findUserByLogin(login);
        if(!Objects.isNull(responseUser)){
            response = new ResponseEntity<>(responseUser, HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("erro ao solicitar usuário com o login informado",HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ResponseEntity response;

        if(service.delete(id)){
            response = new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
        }else {
            response = new ResponseEntity<>("Erro ao tentar deletar usuário", HttpStatus.BAD_REQUEST);
        }
        return response;
    }



}
