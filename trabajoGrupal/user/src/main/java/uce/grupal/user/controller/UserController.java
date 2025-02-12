package uce.grupal.user.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<String> verifyUserFace(
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2) {
        // Compara las imágenes
        String result = facialRecognitionService.compareFaces(image1, image2);

        // Verifica si el resultado es "Confirmado"
        if ("Confirmado".equals(result)) {
            // Obtén los datos faciales (puedes usar un hash de la imagen, por ejemplo)
            String facialData = image1.getOriginalFilename(); // Esto es solo un ejemplo

            // Verifica si el usuario está en la base de datos
            boolean isUserVerified = userService.verifyUser(facialData);
            if (isUserVerified) {
                return ResponseEntity.ok("User verified");
            } else {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(result);
        }
    }


}