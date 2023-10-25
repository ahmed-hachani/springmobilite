package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Converter.FormFieldConverter;
import com.example.PlateformeMobilite.DTO.FormFieldDTO;
import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Entity.FormField;
import com.example.PlateformeMobilite.Interfaces.IFormFieldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class FormFieldController {

    private final IFormFieldService fs;
    private final FormFieldConverter ffc;

    @PostMapping("/addFormField")
    public FormField addFormField (@RequestBody FormField f){
        return fs.addFormField(f);
    }
    @GetMapping("/getFormField")
    public List<FormField> getFormField(){
        return fs.retrieveAllFormFields();
    }
    @PutMapping("/updateFormField/{id}")
    public FormField updateFormField(@PathVariable Long id,@RequestBody FormField f){
        return fs.updateFormField(id,f);
    }
    @DeleteMapping("deleteFormField/{id}")
    public void deleteFormField(@PathVariable Long id){
        fs.removeFormField(id);
    }
    @GetMapping("/byform/{formId}")
    public List<FormField> getFormFieldsByFormId(@PathVariable Long formId) {
        return fs.getFormFieldsByFormId(formId);
    }
    @PostMapping("/addFormFields")
    public List<FormField> addFormFields (@RequestBody List<FormFieldDTO> formFieldDTOS){
        return ffc.convertFormFields(formFieldDTOS);
    }


}
