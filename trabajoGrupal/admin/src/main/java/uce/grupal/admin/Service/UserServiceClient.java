package uce.grupal.admin.Service;

import java.util.List;

import uce.gruapal.shared.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {
    private final RestTemplate restTemplate;

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUserById(Long id) {
        String url = "http://user-service/users/" + id;
        return restTemplate.getForObject(url, User.class);
    }

    public List<User> getAllUsers() {
        String url = "http://user-service/users";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}).getBody();
    }
}
