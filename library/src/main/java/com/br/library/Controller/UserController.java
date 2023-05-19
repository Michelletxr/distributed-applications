package com.br.library.Controller;
import com.br.library.Model.User;
import com.br.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/lib/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping(value="{id}")
    public User getUser(@PathVariable UUID id){
        return userService.getUser(id);
    }

    @PostMapping(value="register")
    public User register(@RequestBody UserService.UserRegister user){
        return userService.registryUser(user);
    }

}
