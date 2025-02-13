package uce.grupal.user.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uce.grupal.user.model.FacialRecognitionService;
import uce.grupal.user.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final FacialRecognitionService facialRecognitionService;

    @Autowired
    public UserController(UserService userService, FacialRecognitionService facialRecognitionService) {
        this.userService = userService;
        this.facialRecognitionService = facialRecognitionService;
    }

    @PostMapping("/verify-face")
    public ResponseEntity<String> verifyUserFace(
            @RequestParam("userId") Long userId, 
            @RequestParam("image") MultipartFile image) {  
        try {
            if (!userService.userExists(userId)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            boolean isMatch = facialRecognitionService.verifyUserImage(userId, image);

            if (isMatch) {
                return ResponseEntity.ok("User verified");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image does not match");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
        }
    }
}
