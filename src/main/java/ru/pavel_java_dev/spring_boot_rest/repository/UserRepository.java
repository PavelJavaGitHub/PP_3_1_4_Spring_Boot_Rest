package ru.pavel_java_dev.spring_boot_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pavel_java_dev.spring_boot_rest.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
