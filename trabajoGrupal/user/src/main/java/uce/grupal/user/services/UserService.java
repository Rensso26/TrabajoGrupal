package uce.grupal.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uce.grupal.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean verifyUser(String facialData) {
        return userRepository.findByFacialData(facialData).isPresent();
    }
}