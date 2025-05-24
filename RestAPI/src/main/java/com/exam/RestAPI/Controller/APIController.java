package com.exam.RestAPI.Controller;

import com.exam.RestAPI.Models.User;
import com.exam.RestAPI.Repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class APIController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getPage() {
        return "Root!";
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/user")
    public String  createUser(@RequestBody @Valid User user) {
        try {
            userRepo.save(user);
            return "User created!";
        }catch (Exception exception) {
            return "Error creating user: " + exception.getMessage();
        }
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable long id, @Valid @RequestBody User user) {
        try {
            Optional<User> optionalUser = userRepo.findById(id);

            if (optionalUser.isPresent()) {
                User updatedUser = optionalUser.get();

                updatedUser.setFirstName(user.getFirstName());
                updatedUser.setLastName(user.getLastName());
                updatedUser.setOccupation(user.getOccupation());
                updatedUser.setAge(user.getAge());

                userRepo.save(updatedUser);

                return "User updated successfully!";
            } else {
                return "Error: User with ID " + id + " not found.";
            }
        } catch (Exception exception) {
            return "Error updating user!" + exception.getMessage();
        }

    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id) {
        try{
            Optional<User> optionalUser = userRepo.findById(id);

            if (optionalUser.isPresent()) {
                User deleteUser = optionalUser.get();

                userRepo.delete(deleteUser);

                return "User deleted!";
            } else {
                return "Error: User with ID " + id + " not found.";
            }


        }catch (Exception exception){
            return "Error deleting user!" + exception;
        }
    }

}
