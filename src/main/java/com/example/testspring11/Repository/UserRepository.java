package com.example.testspring11.Repository;

import com.example.testspring11.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
