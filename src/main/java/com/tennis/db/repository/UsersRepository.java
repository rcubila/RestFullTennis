package com.tennis.db.repository;

import com.tennis.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<List<User>> findByName(String name);
}
