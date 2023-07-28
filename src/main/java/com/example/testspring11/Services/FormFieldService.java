package com.example.testspring11.Services;

import com.example.testspring11.Entity.FormField;
import com.example.testspring11.Repository.FormFieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FormFieldService implements IFormFieldService{

    FormFieldRepository formFieldRepository;
    @Override
    public List<FormField> retrieveAllFormFields() {
        return formFieldRepository.findAll();
    }

    @Override
    public FormField retrieveFormFieldById(Long formFieldId) {
        return formFieldRepository.findById(formFieldId).get();
    }

    @Override
    public FormField addFormField(FormField f) {
        return formFieldRepository.save(f);
    }

    @Override
    public FormField updateFormField(FormField f) {
        return formFieldRepository.save(f);
    }

    @Override
    public void removeFormField(Long FormFieldId) {
        formFieldRepository.deleteById(FormFieldId);

    }
}
