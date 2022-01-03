package com.dere.codesvalidate.controllers;

import com.dere.codesvalidate.models.Configuration;
import com.dere.codesvalidate.models.User;
import com.dere.codesvalidate.repository.CodeRepository;
import com.dere.codesvalidate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class UserController {

    Logger logger = Logger.getLogger("User Controller");

    @Autowired
    UserRepository userRepository;

    @Autowired
    CodeRepository codeRepository;

    @RequestMapping(path = "/user" , method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers(){
        try{
            List<User> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(path = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable String userId){
        try {
            if(userRepository.existsById(userId)) {
                Optional<User> user = userRepository.findById(userId);
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Doesn't Exist");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User user){
        try {
            userRepository.save(user);
            return ResponseEntity.ok("User created "+ user.getUserId());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public ResponseEntity<?> addCodesToUser(@Valid @RequestBody User user){
        Configuration configuration = new Configuration();
        configuration.setPointValue(1);
        try{
            if(userRepository.existsById(user.getUserId())){
                Long userPoint  = userRepository.findById(user.getUserId()).get().getPoints();
                user.setPoints(userPoint*configuration.getPointValue());
                userRepository.save(user);
                return ResponseEntity.ok("Update successful");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Doesn't Exist");

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/getUserPoint/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPoint(@PathVariable String userId){
        try {
            long codes = userRepository.findById(userId).get().getPoints();
            //int codeValue = codeRepository.findAll().get(0).getPointValue();
            return ResponseEntity.ok(codes);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
