package com.edevs.springadlister.repositories;

import com.edevs.springadlister.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
