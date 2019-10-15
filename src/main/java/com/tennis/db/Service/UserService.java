package com.tennis.db.Service;

import com.tennis.db.model.User;
import com.tennis.db.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getAll() {
        return usersRepository.findAll();
    }

    public ResponseEntity<User> getUserById(int id) {
        User user = usersRepository.getOne(id);
        return  ResponseEntity.ok(user);
    }

    public List<User> getUserByName(String name) {
        Optional<List<User>> listOptional = usersRepository.findByName(name);
        return listOptional.orElse(new ArrayList<>());
    }

    public List<User> persist(User user) {
        usersRepository.save(user);
        return usersRepository.findAll();
    }

    public User updateUser(Integer id, User userSelected){
        User user = usersRepository.getOne(id);
//      user.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username));
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        return usersRepository.save(user);
    }

    public ResponseEntity<User> delete(int id) {
        usersRepository.delete(id);
        return ResponseEntity.noContent().build();

    }

}
