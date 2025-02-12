package uce.grupal.admin.repository;

import uce.grupal.admin.model.UserImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserImageRepository extends MongoRepository<UserImage, String> {
}