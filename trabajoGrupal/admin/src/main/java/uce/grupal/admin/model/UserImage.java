package uce.grupal.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "user_images")
@Getter
@Setter
public class UserImage {
    @Id
    private String id;
    private String userId; // ID del usuario asociado a la imagen
    private String imageBase64; // Imagen en formato base64

}