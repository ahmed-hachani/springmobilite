package com.example.testspring11.Controller;

import com.example.testspring11.Entity.University;
import com.example.testspring11.Services.IUniversityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversityController {
    IUniversityService ur;
    @PostMapping("/addUniversity")
    public University addUniversity(@RequestBody University u){
        return ur.addUniversity(u);

    }
}
