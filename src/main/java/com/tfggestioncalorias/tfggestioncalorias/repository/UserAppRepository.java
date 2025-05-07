package com.tfggestioncalorias.tfggestioncalorias.repository;

import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findByEmailContaining(String emailUser);

    UserApp findByEmail(String email);

}
