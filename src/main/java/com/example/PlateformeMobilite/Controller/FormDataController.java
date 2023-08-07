package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.Entity.FormData;
import com.example.PlateformeMobilite.Interfaces.IFormDataService;
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
