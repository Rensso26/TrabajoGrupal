package uce.grupal.admin.Controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import uce.gruapal.shared.repository.UserRepository;
import uce.grupal.admin.model.UserImage;
import uce.grupal.admin.repository.UserImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uce.gruapal.shared.model.User;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImageRepository userImageRepository;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

     @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("userId") String userId,
            @RequestParam("image") MultipartFile image) {
        try {
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());

            UserImage userImage = new UserImage();
            userImage.setUserId(userId);
            userImage.setImageBase64(imageBase64);
            userImageRepository.save(userImage);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

}