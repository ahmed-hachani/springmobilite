package com.example.PlateformeMobilite.Services;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import com.example.PlateformeMobilite.Repository.FormRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class FormService implements IFormService {
    private final FormRepository formRepository;
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
    public Form updateForm(Long id,Form f) {
        return formRepository.findById(id)
                .map(p->{
                    p.setDescription(f.getDescription());
                    p.setFormName((f.getFormName()));
                    return formRepository.save(f);
                }).orElseThrow(()-> new RuntimeException("Form not found!"));
    }

    @Override
    public void removeForm(Long formId) {
        formRepository.deleteById(formId);

    }
}
