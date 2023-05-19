package com.br.library.Service;
import com.br.library.Model.User;
import com.br.library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    String authUrl = "http://localhost:8082/api/auth/";
    String notificationsUrl = "http://localhost:8083/api/notifications/send-email";

    public record UserRegister(String userName, String login, String password, String email){}
    public record SendEmail(String emailFrom, String emailTo, String title, String text){}

    public User registryUser(UserRegister user){
        HttpEntity<UserRegister> request = new HttpEntity<>(user);
        User userResponse = null;
        ResponseEntity response = restTemplate
                .exchange(authUrl, HttpMethod.POST, request, User.class);
        if(response.getStatusCode() == HttpStatus.OK){
            userResponse = save((User) response.getBody());
            sendNotifications(userResponse.getEmail());
        }

        return userResponse;
    }

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

    public User getUser(UUID id){
        User userResponse = null;
        ResponseEntity response = restTemplate
                .getForEntity(authUrl.concat(String.valueOf(id)), User.class);
        if(response.getStatusCode() == HttpStatus.OK){
            userResponse = (User) response.getBody();
        }
        return userResponse;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user){
        return userRepository.save(user);
    }
}
