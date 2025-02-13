package uce.grupal.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.gruapal.shared.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    public boolean verifyUser(String facialData) {
        return userRepository.findByFacialData(facialData).isPresent();
    }
}