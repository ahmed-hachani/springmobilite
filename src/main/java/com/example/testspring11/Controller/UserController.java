package com.example.testspring11.Controller;


import com.example.testspring11.Entity.User;
import com.example.testspring11.Services.IUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    IUserService iUserService;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User u){
        return iUserService.addUser(u);
    }
}
