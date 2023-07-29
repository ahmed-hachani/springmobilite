package com.example.testspring11.Services;

import com.example.testspring11.Entity.FormField;

import java.util.List;

public interface IFormFieldService {
    public List<FormField> retrieveAllFormFields();
    public FormField retrieveFormFieldById(Long formFieldId);
    public FormField addFormField(FormField f);
    public FormField updateFormField(Long id,FormField f);
    public void removeFormField (Long FormFieldId);
}
