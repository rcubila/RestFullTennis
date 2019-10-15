package com.tennis.db.controller;

import com.tennis.db.Service.UserService;
import com.tennis.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping(value = {"/users"})
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allUsers")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({"/validateLogin"})
    public User validateLogin() {
        return new User("User successfully authenticated");
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<ResponseEntity<User>> getUserById(@PathVariable Integer id) {

        return Optional.ofNullable(userService.getUserById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping(value = "/userBy/{name}")
    public List<User> getUserByName(@PathVariable(value = "name") String name) {
        return userService.getUserByName(name);
    }

    @PostMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> persist(@RequestBody User user) {
        return userService.persist(user);
    }

    @PutMapping(value = "updateUser/{id}/{name}")
    public User updateUser(@PathVariable(value = "id") Integer id, @Valid @RequestBody User userSelected) {
        return userService.updateUser(id, userSelected);
    }

        @DeleteMapping(path = {"/{id}" })
    public void delete(@PathVariable("id") int id) {
        userService.delete((id));
    }
}
