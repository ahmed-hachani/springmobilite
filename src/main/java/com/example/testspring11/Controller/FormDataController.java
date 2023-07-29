package com.example.testspring11.Controller;


import com.example.testspring11.Entity.Form;
import com.example.testspring11.Entity.FormData;
import com.example.testspring11.Services.IFormDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FormDataController {
    private final IFormDataService fs;

    @PostMapping("/addFormData")
    public FormData addFormData (FormData f){
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
}
