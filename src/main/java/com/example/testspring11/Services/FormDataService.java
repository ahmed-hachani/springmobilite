package com.example.testspring11.Services;

import com.example.testspring11.Entity.FormData;
import com.example.testspring11.Repository.FormDataRepository;
import com.example.testspring11.Repository.FormFieldRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FormDataService implements IFormDataService{

    private final FormDataRepository fd;

    @Override
    public List<FormData> retrieveAllFormData() {
        return fd.findAll();
    }

    @Override
    public FormData retrieveFormDataById(Long FormDataId) {
        return fd.findById(FormDataId).get();
    }

    @Override
    public FormData addFormData(FormData f) {
        return fd.save(f);
    }

    @Override
    public FormData updateFormData(Long id,FormData f) {
         return fd.findById(id)
                .map(p->{
                    p.setValue(f.getValue());

                    return fd.save(f);
                }).orElseThrow(()-> new RuntimeException("FormData not found!"));
    }

    @Override
    public void removeFormData(Long FormDataId) {
        fd.deleteById(FormDataId);

    }
}
