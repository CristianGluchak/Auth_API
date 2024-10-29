package com.example.api_login_auth.repository;

import com.example.api_login_auth.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 27/10/2024
 */
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String Email);
}
