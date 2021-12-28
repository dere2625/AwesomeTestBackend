package com.dere.codesvalidate.controllers;

import com.dere.codesvalidate.models.Code;
import com.dere.codesvalidate.repository.CodeRepository;
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

    @RequestMapping(path = "/codes", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCodes(@Valid @RequestBody List<Code> codes){
        try{
            codeRepository.saveAll(codes);
            return ResponseEntity.ok("Codes Added");
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(path = "/validateCodes/{code}" , method = RequestMethod.GET)
    public ResponseEntity<?> validateCodes(@PathVariable String code){
        try{
            if(isCodeAvailable(code) && !isCodeUsed(code)){
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



    public boolean isCodeAvailable(String code){
        return codeRepository.existsById(code);
    }

    public boolean isCodeUsed(String code){
        Optional<Code> promptedCode = codeRepository.findById(code);
        return promptedCode.map(Code::isUsed).orElse(false);
    }


}
