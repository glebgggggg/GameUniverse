package com.gameUniverse.GameUniverse.repositories;

import com.gameUniverse.GameUniverse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
