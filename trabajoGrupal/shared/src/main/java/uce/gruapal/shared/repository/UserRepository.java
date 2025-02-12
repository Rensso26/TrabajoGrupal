package uce.gruapal.shared.repository;

import uce.gruapal.shared.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}