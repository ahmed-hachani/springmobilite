package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormFieldService;
import com.example.PlateformeMobilite.Repository.FormFieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FormFieldService implements IFormFieldService {

    private final FormFieldRepository formFieldRepository;
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
    public FormField updateFormField(Long id,FormField f) {
        return formFieldRepository.findById(id)
                .map(p->{
                    p.setFieldType(f.getFieldType());
                    p.setFieldName((f.getFieldName()));
                    return formFieldRepository.save(f);
                }).orElseThrow(()-> new RuntimeException("Form not found!"));
    }

    @Override
    public void removeFormField(Long FormFieldId) {
        formFieldRepository.deleteById(FormFieldId);

    }
}
