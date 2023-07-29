package com.example.testspring11.Services;

import com.example.testspring11.Entity.User;

import java.util.List;

public interface IUserService {
    public List<User> retrieveAllUsers();
    public User retrieveUser(Long userID);
    public User addUser(User u);
    public User updateUser(Long id,User u);
    public void removeUser(Long userID);


}
