package uce.grupal.user.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uce.grupal.user.model.FacialRecognitionService;
import uce.grupal.user.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private FacialRecognitionService facialRecognitionService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody String facialData) {
        boolean isUserVerified = userService.verifyUser(facialData);
        if (isUserVerified) {
            return ResponseEntity.ok("User verified");
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("User not found");
        }
    }

    @PostMapping("/verify-face")
    public ResponseEntity<String> verifyUserFace() {
        String facialData = facialRecognitionService.scanFace();
        boolean isUserVerified = userService.verifyUser(facialData);
        if (isUserVerified) {
            return ResponseEntity.ok("User verified");
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("User not found");
        }
    }


}