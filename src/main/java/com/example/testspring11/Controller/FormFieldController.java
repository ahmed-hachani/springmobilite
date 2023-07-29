package com.example.testspring11.Controller;

import com.example.testspring11.Entity.Form;
import com.example.testspring11.Entity.FormField;
import com.example.testspring11.Services.IFormFieldService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FormFieldController {

    private final IFormFieldService fs;

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


}
