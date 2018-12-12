package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/platform")
    public ResponseEntity<List<Platform>> getAllPlatform() {
        return new ResponseEntity<>(platformService.getAllPlatform(), HttpStatus.OK);
    }

    @GetMapping("/platform/{id}")
    public ResponseEntity<Platform> getPlatform(@PathVariable long id) {
        return new ResponseEntity<>(platformService.getPlatform(id), HttpStatus.OK);
    }

    @PostMapping("/platform")
    public ResponseEntity<Platform> savePlatform(@Valid @RequestBody Platform platform) {
        return new ResponseEntity<>(platformService.savePlatform(platform), HttpStatus.CREATED);
    }

    @PutMapping("/platform/{id}")
    public ResponseEntity<Platform> updatePlatform(@PathVariable long id,@Valid @RequestBody Platform updatedPlatform) {
        return new ResponseEntity<>(platformService.updatePlatform(id, updatedPlatform), HttpStatus.OK);
    }

    @DeleteMapping("/platform/{id}")
    public ResponseEntity<List<Platform>> deletePlatform(@PathVariable long id) {
        return new ResponseEntity<>(platformService.deletePlatform(id), HttpStatus.OK);
    }
}
