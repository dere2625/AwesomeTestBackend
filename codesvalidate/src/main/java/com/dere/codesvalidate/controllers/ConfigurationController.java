package com.dere.codesvalidate.controllers;

import com.dere.codesvalidate.models.Configuration;
import com.dere.codesvalidate.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "https://localhost:44317",allowedHeaders = "*")
@RestController
public class ConfigurationController {

    @Autowired
    ConfigurationRepository configurationRepository;

    public ConfigurationController(){

    }

    @CrossOrigin
    @RequestMapping(path = "/codeValue/{codeValue}", method = RequestMethod.GET)
    public ResponseEntity<?> assignCodeValue(@PathVariable int codeValue){
        Optional<Configuration> configuration = configurationRepository.findById("globalConfig");
        configuration.get().setPointValue(codeValue);
        configurationRepository.save(configuration.get());
        return ResponseEntity.ok().body("success");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/callToAction/{callToAction}", method = RequestMethod.GET)
    public ResponseEntity<?> assignCallToAction(@PathVariable String callToAction){
        Optional<Configuration> configuration = configurationRepository.findById("globalConfig");
        configuration.get().setCallToAction(callToAction);
        configurationRepository.save(configuration.get());
        return ResponseEntity.ok().body("success");
    }

    @RequestMapping(path = "/background/{background}", method = RequestMethod.GET)
    public ResponseEntity<?> assignBackground(@PathVariable String background){
        Optional<Configuration> configuration = configurationRepository.findById("globalConfig");
        configuration.get().setBackgroundColor(background);
        configurationRepository.save(configuration.get());
        return ResponseEntity.ok().body("success");
    }

    @RequestMapping(path = "/hero/{location}", method = RequestMethod.GET)
    public ResponseEntity<?> heroLocation(@PathVariable String location){
        Optional<Configuration> configuration = configurationRepository.findById("globalConfig");
        configuration.get().setHeroLocation(location);
        configurationRepository.save(configuration.get());
        return ResponseEntity.ok().body("success");
    }

    @RequestMapping(path = "/allconfig", method = RequestMethod.GET)
    public ResponseEntity<?> allConfiguration(){
        Optional<Configuration> configuration = configurationRepository.findById("globalConfig");
        return ResponseEntity.ok(configuration);
    }
}
