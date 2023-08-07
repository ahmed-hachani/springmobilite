package com.example.PlateformeMobilite.Controller;


import com.example.PlateformeMobilite.Entity.User;
import com.example.PlateformeMobilite.Interfaces.IUserService;
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
