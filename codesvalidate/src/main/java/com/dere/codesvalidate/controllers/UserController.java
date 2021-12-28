package com.dere.codesvalidate.controllers;

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
        try{
            if(userRepository.existsById(user.getUserId())){
                List<String> codesList = Stream.of(user.getCodes(), userRepository.findById(user.getUserId()).get().getCodes()).flatMap(Collection::stream).collect(Collectors.toList());
                user.setCodes(codesList);
                userRepository.save(user);
                return ResponseEntity.ok("Update successful");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Doesn't Exist");

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @RequestMapping(path = "/getUserPoint", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPoint(@Valid @RequestBody User user){
        try {
            int codes = userRepository.findById(user.getUserId()).get().getCodes().size();
            int codeValue = codeRepository.findAll().get(0).getPointValue();
            return ResponseEntity.ok(codes*codeValue);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
