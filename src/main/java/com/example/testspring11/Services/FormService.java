package com.example.testspring11.Services;

import com.example.testspring11.Entity.Form;
import com.example.testspring11.Repository.FormRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class FormService implements IFormService{
    FormRepository formRepository;
    @Override
    public List<Form> retrieveAllForms() {
        return formRepository.findAll();
    }

    @Override
    public Form retrieveFormById(Long formId) {
        return formRepository.findById(formId).get();
    }

    @Override
    public Form addForm(Form f) {
        return formRepository.save(f);
    }

    @Override
    public Form updateForm(Form f) {
        return formRepository.save(f);
    }

    @Override
    public void removeForm(Long formId) {
        formRepository.deleteById(formId);

    }
}
