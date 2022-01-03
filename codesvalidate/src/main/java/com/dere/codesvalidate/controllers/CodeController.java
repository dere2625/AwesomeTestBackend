package com.dere.codesvalidate.controllers;

import com.dere.codesvalidate.models.Code;
import com.dere.codesvalidate.models.Configuration;
import com.dere.codesvalidate.models.User;
import com.dere.codesvalidate.repository.CodeRepository;
import com.dere.codesvalidate.repository.ConfigurationRepository;
import com.dere.codesvalidate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CodeController {

    @Autowired
    CodeRepository codeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConfigurationRepository configurationRepository;

    @RequestMapping(path = "/codes", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCodes(){
        return ResponseEntity.ok(codeRepository.findAll());
    }

    @RequestMapping(path = "/codes", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCodes(@Valid @RequestBody List<Code> codes){
        try{
            codeRepository.saveAll(codes);
            return ResponseEntity.ok("Codes Added");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(path = "/validateCodes/{userId}/{code}" , method = RequestMethod.GET)
    public ResponseEntity<?> validateCodes(@PathVariable String code, @PathVariable String userId){
        try{
            if(isCodeAvailable(code) && !isCodeUsed(code) && isUserAvailable(userId)){
                setCodeUsed(code);
                addPointToUser(userId,code);
                return ResponseEntity.ok("Confirmed");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid code");
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occurred");
        }
    }

    @RequestMapping(path = "/updateCodeValue", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeValue(@RequestBody @Valid Code code){
        try {
            if(isCodeAvailable(code.getCode())){
                return null;
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code doesn't Exist");
            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(path = "/transfer/{fromUser}/{toUser}/{point}", method = RequestMethod.GET)
    public ResponseEntity<?> transferPoints(@PathVariable String fromUser, @PathVariable String toUser, @PathVariable int point){
        if(!userHasEnoughPoints(fromUser,point) || !isUserAvailable(fromUser) || !isUserAvailable(toUser)){
            return ResponseEntity.badRequest().body("Failed");
        }
        else{
            Optional<User> fromUserUser = userRepository.findById(fromUser);
            Optional<User> toUserUser = userRepository.findById(toUser);
            fromUserUser.get().setPoints(fromUserUser.get().getPoints()-point);
            toUserUser.get().setPoints(toUserUser.get().getPoints()+point);

            userRepository.save(fromUserUser.get());
            userRepository.save(toUserUser.get());

            return ResponseEntity.ok("Success");
        }
    }



    public boolean isCodeAvailable(String code){
        return codeRepository.existsById(code);
    }

    public void addPointToUser(String userId, String point){
        Optional<User> user = userRepository.findById(userId);
        user.get().setUserId(userId);
        user.get().setPoints(user.get().getPoints() + configurationRepository.findById("globalConfig").get().getPointValue());
        userRepository.save(user.get());
    }

    public boolean isCodeUsed(String code){
        Optional<Code> promptedCode = codeRepository.findById(code);
        return promptedCode.map(Code::isUsed).orElse(false);
    }

    public boolean isUserAvailable(String userId){
        return userRepository.existsById(userId);
    }

    public void setCodeUsed(String code){
        Optional<Code> promptedCode = codeRepository.findById(code);
        promptedCode.get().setUsed(true);
        codeRepository.save(promptedCode.get());
    }

    public boolean userHasEnoughPoints(String userId, int point){
        Optional<User> user = userRepository.findById(userId);
        if(user.get().getPoints() < point){
            return false;
        }
        return true;
    }

}
