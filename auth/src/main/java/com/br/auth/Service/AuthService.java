package com.br.auth.Service;

import com.br.auth.model.User;
import com.br.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;



    public User.UserRecord findById(UUID id){
        Optional<User> user = repository.findById(id);
        User.UserRecord responseUser = null;
        if(user.isPresent()){
            User userValue = user.get();
            responseUser = buildUserToResponseUser(userValue);
        }
        return responseUser;
    }
    public User Save(User.UserRecord requestUser){
        User createUser = null;
        if(!Objects.isNull(requestUser)){

                User user = new User().builder()
                        .userName(requestUser.userName())
                        .login(requestUser.login())
                        .password(passwordEncoder.encode(requestUser.password()))
                        .email(requestUser.email())
                        .build();

                createUser = repository.save(user);
        }
        return createUser;
    }

    public User.UserRecord findUserByLogin(String login){
        User.UserRecord responseUser = null;
        User user = repository.findByLogin(login);
        if(!Objects.isNull(user)){
            responseUser = buildUserToResponseUser(user);
        }
        return responseUser;
    }



    public boolean delete(UUID id){
        boolean isDelet = false;
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            isDelet = true;
        }
        return isDelet;
    }

    public static User.UserRecord buildUserToResponseUser(User user){
        return new User.UserRecord(
                user.getUsername(),
                user.getLogin(),
                "",
                user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username);
    }

    public List<User.UserRecord> findAll() {
        List<User.UserRecord> userRecords = new ArrayList<>();
        for (User u: repository.findAll()) {
            userRecords.add(buildUserToResponseUser(u));
        }
        return userRecords;
    }
}
