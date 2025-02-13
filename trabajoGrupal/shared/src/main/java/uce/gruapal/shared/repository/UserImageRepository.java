package uce.gruapal.shared.repository;

import uce.gruapal.shared.model.UserImage;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserImageRepository extends MongoRepository<UserImage, String> {
    List<UserImage> findByUserId(String userId);
 }