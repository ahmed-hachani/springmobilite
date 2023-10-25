package com.example.PlateformeMobilite.Repository;

import com.example.PlateformeMobilite.Entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository <Form,Long> {
    Form findByFormId(Long formId);
    Optional<Form> findByFormName(String formName);

}
