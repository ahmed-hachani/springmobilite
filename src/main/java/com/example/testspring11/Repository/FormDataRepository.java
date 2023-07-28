package com.example.testspring11.Repository;

import com.example.testspring11.Entity.FormData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormDataRepository extends JpaRepository<FormData,Long> {
}
