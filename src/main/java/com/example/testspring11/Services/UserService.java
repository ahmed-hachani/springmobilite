package com.example.testspring11.Services;

import com.example.testspring11.Entity.User;
import com.example.testspring11.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    UserRepository userRepository;

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User retrieveUser(Long userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public void removeUser(Long userID) {
        userRepository.deleteById(userID);

    }
//    BusRepository Clr;



//    @Override
//    public void affecterUtilisateurClasse(Integer idUtilisateur, Integer codeClasse) {
//        Utilisateur u= Ur.findById(idUtilisateur).orElse(null);
//        Bus c=Clr.findById(codeClasse).orElse(null);
//        u.setClassee(c);
//    }

}
