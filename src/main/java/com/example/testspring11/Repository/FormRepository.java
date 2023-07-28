package com.example.testspring11.Repository;

import com.example.testspring11.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository <Form,Long> {
}
