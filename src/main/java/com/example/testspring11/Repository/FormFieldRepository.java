package com.example.testspring11.Repository;

import com.example.testspring11.Entity.FormField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFieldRepository extends JpaRepository<FormField,Long> {
}
