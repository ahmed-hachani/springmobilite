package com.example.testspring11.Services;

import com.example.testspring11.Entity.FormData;

import java.util.List;

public interface IFormDataService {
    public List<FormData> retrieveAllFormData();
    public FormData retrieveFormDataById(Long FormDataId);
    public FormData addFormData(FormData f);
    public FormData updateFormData(FormData f);
    public void removeFormData(Long FormDataId);
}
