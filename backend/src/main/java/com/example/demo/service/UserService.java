package com.example.demo.service;

import com.example.demo.data.User;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(String id) {
        return userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public User findByName(String userName) {
        return userRepo.findUserByUserName(userName).orElseThrow(EntityNotFoundException::new);
    }

    public User findByEmail(String email) {
        return userRepo.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    public User checkPassword(String email, String password) {
        return userRepo.findUserByEmailAndPassword(email, password).orElseThrow(EntityNotFoundException::new);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void deleteById(String id) {
        userRepo.deleteById(id);
    }
}