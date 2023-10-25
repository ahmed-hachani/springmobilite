package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.DTO.FormDataDTO;
import com.example.PlateformeMobilite.Entity.*;
import com.example.PlateformeMobilite.Interfaces.IFormDataService;
import com.example.PlateformeMobilite.Interfaces.IFormFieldService;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import com.example.PlateformeMobilite.Interfaces.IUserService;
import com.example.PlateformeMobilite.Repository.FormDataRepository;
import com.example.PlateformeMobilite.Repository.MoyenneRepository;
import com.example.PlateformeMobilite.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class FormDataController {
    private final IFormDataService fs;
    private final IUserService us;
    private final IFormService formservice;
    private final IFormFieldService ffs;
    private final MoyenneRepository moyenneRepository;
    private final MoyenneService moyenneService;
    private final FormDataRepository formDataRepository;

    @PostMapping("/addFormData")
    public FormData addFormData (@RequestBody FormData f){
        return fs.addFormData(f);
    }
    @GetMapping("/getFormData")
    public List<FormData> getFormData(){
        return fs.retrieveAllFormData();
    }
    @PutMapping("updateFormData/{id}")
    public FormData updateFormData (@PathVariable Long id, @RequestBody FormData f){
        return fs.updateFormData(id,f);
    }
    @DeleteMapping("/deleteFormData/{id}")
    public void deleteFormData(@PathVariable Long id){
        fs.removeFormData(id);
    }
//    @PostMapping("/submitFormData")
//    public ResponseEntity<String> submitFormData(@RequestBody FormData formData){
//
//    }
@PostMapping("/submitFormData")
public FormData addFormData(@RequestBody FormDataDTO formDataDTO) {
    // Fetch user, form, and formField based on provided IDs
    User user = us.retrieveUser(formDataDTO.getUserId());
//    Form form = formservice.retrieveFormById(formDataDTO.getFormId());
    FormField formField = ffs.retrieveFormFieldById(formDataDTO.getFormFieldId());
    Form form= formField.getForm();

    // Create a new FormData object and set the values
    FormData formData = new FormData();
    formData.setUser(user);
    System.out.println(user);
//    formData.setForm(form);
    formData.setField(formField);
    formData.setValue(formDataDTO.getValue());

    // Save the FormData object
    return fs.addFormData(formData);
}
    @PostMapping("/submitFormDatas")
    public List<FormData> addFormData(@RequestBody List<FormDataDTO> formDataDTOList) {
        // Create a list to store the FormData objects
        List<FormData> formDataList = new ArrayList<>();
        User loopUser = null; // Initialize to null
        Form loopForm = null; // Initialize to null

        // Iterate through each FormDataDTO in the list and process it
        for (FormDataDTO formDataDTO : formDataDTOList) {
            Long userId = formDataDTO.getUserId();
            Long formFieldId = formDataDTO.getFormFieldId();
            String value = formDataDTO.getValue();

            // Fetch user, form, and formField based on provided IDs
            User user = us.retrieveUser(userId);
            FormField formField = ffs.retrieveFormFieldById(formFieldId);
            Form form = formField.getForm();

            // Check if form data already exists for the user and form
            FormData existingFormData = formDataRepository.findByUser_UserIdAndField_FieldId(userId, formFieldId);
            if (existingFormData != null) {
                // Form data exists; update it
                existingFormData.setValue(value);
                formDataRepository.save(existingFormData);
            } else {
                // Form data does not exist; create a new one
                // Create a new FormData object and set the values
                FormData formData = new FormData();
                formData.setUser(user);
                formData.setForm(form);
                formData.setField(formField);
                formData.setValue(formDataDTO.getValue());

                // Save the FormData object and add it to the list
                formDataList.add(fs.addFormData(formData));

                // Collect user and form only if they are not collected yet
                if (loopUser == null) {
                    loopUser = user;
                }
                if (loopForm == null) {
                    loopForm = form;
                }
            }
        }

        // Calculate the moyenne based on the collected user and form
        if (loopUser != null && loopForm != null) {
            Moyenne moyenne = new Moyenne();
            moyenne.setUser(loopUser);
            moyenne.setForm(loopForm);
            double calculatedMoyenne = moyenneService.calculateAverageForUser(loopForm.getFormId(), loopUser.getUserId());
            moyenne.setMoyenne(calculatedMoyenne);

            // Save the Moyenne entity to the database
            moyenneRepository.save(moyenne);
        }

        // Return the list of FormData objects
        return formDataList;
    }


}
