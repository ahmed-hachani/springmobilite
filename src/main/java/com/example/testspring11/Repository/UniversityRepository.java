package com.example.testspring11.Repository;

import com.example.testspring11.Entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University,Long> {
}
