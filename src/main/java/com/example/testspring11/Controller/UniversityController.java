package com.example.testspring11.Controller;

import com.example.testspring11.Entity.University;
import com.example.testspring11.Services.IUniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UniversityController {

    private final IUniversityService ur;

    @PostMapping("/addUniversity")
    public University addUniversity(@RequestBody University u){
        return ur.addUniversity(u);

    }
    @GetMapping("/getUniversities")
    public List<University> getUniversities(){
        return ur.retrieveAllUniversities();
    }
    @PutMapping("/updateUniversity/{id}")
    public University updateUniversity(@PathVariable Long id,@RequestBody University u){
        return ur.updateUniversity(id,u);
    }
    @DeleteMapping("/deleteUniversity/{id}")
    public void deleteUniversity (@PathVariable Long id){
        ur.removeUniversity(id);
    }
}
