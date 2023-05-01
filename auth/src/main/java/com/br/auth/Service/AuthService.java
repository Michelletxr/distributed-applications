package com.br.auth.Service;

import com.br.auth.model.User;
import com.br.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthRepository repository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public User.UserRecord findById(UUID id){
        Optional<User> user = repository.findById(id);
        User.UserRecord responseUser = null;
        if(user.isPresent()){
            User userValue = user.get();
            responseUser = buildUserToResponseUser(userValue);
        }
        return responseUser;
    }
    public UUID Save(User.UserRecord requestUser){
        UUID userId = null;
        if(!Objects.isNull(requestUser)){

                User user = new User().builder()
                        .userName(requestUser.userName())
                        .login(requestUser.login())
                        .password(passwordEncoder().encode(requestUser.password()))
                        .email(requestUser.email())
                        .build();

                User createUser = repository.save(user);
                userId = createUser.getId();
        }
        return userId;
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
                user.getPassword(),
                user.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username);
    }
}
