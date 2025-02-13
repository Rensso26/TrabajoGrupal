package uce.grupal.user.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uce.gruapal.shared.model.UserImage;
import uce.gruapal.shared.repository.UserImageRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class FacialRecognitionService {
    @Autowired
    private UserImageRepository userImageRepository;

    public boolean verifyUserImage(Long userId, MultipartFile image) throws IOException {
        List<UserImage> userImages = userImageRepository.findByUserId(userId.toString());
        String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
        for (UserImage userImage : userImages) {
            if (userImage.getImageBase64().equals(imageBase64)) {
                return true; 
            }
        }

        return false; 
    }
}