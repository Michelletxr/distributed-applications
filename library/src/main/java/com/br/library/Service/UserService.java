package com.br.library.Service;
import com.br.library.Model.User;
import com.br.library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceFeign serviceFeign;

    String authUrl = "http://localhost:8082/api/auth/";
    String notificationsUrl = "http://localhost:8083/api/notifications/send-email";

    public record UserRegister(String userName, String email){}
    public record SendEmail(String emailFrom, String emailTo, String title, String text){}


    public void sendNotifications(String emailTo){
        System.out.println("enviando mensagem para: "+ emailTo);
        String emailFrom = "michelle.teixeira.124@ufrn.edu.br";
        String title = "Registo";
        String text= "Seja bem-vindo a livraria virtual!";
        HttpEntity<SendEmail> request = new HttpEntity<>(new SendEmail(emailFrom, emailTo, title, text));
        ResponseEntity response = restTemplate
                .exchange(notificationsUrl, HttpMethod.POST, request, String.class);
        System.out.println(response.getBody());
    }

    public UserRegister getUser(UUID id){
        User user = serviceFeign.getUserById(id);
        if(!Objects.isNull(user)){

            return new UserRegister(user.getUserName(), user.getEmail());
        }
        return null;
    }

    public User registryUser(UserRegister user){
        return serviceFeign.registryUser(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user){
        return userRepository.save(user);
    }
}
