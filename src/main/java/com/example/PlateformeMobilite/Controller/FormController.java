package com.example.PlateformeMobilite.Controller;

import com.example.PlateformeMobilite.Entity.Form;
import com.example.PlateformeMobilite.Interfaces.IFormService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FormController {
    private final IFormService fs;

    @PostMapping("/addForm")
    public Form addForm (@RequestBody Form f){
        return fs.addForm(f);
    }
    @GetMapping("/getForm")
    public List<Form> getForm(){
        return fs.retrieveAllForms();
    }
    @PutMapping("/updateForm/{id}")
    public Form updateForm(@PathVariable Long id,@RequestBody Form f){
        return fs.updateForm(id,f);
    }
    @DeleteMapping("/deleteForm/{id}")
    public void deleteForm(@PathVariable Long id){
         fs.removeForm(id);
    }
}
