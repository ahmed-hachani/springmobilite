package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
    public User findByEmail(String email);


    Boolean existsByEmail(String email);
}
