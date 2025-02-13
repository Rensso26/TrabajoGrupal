package uce.grupal.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import uce.gruapal.shared.model.UserImage;

import java.util.List;

public interface UserImageRepository extends MongoRepository<UserImage, String> {
    List<UserImage> findByUserId(String userId);  // Busca imágenes por userId
}