package com.dere.codesvalidate.controllers;

import com.dere.codesvalidate.models.Code;
import com.dere.codesvalidate.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    @Autowired
    CodeRepository codeRepository;

    @RequestMapping(path = "/user" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(codeRepository.findAll());
    }

    @RequestMapping(path = "/codes", method = RequestMethod.POST)
    public ResponseEntity<?> addNewCodes(@RequestBody Code codes){
        codeRepository.save(codes);
        return ResponseEntity.ok("Successfully Added");
    }

}
