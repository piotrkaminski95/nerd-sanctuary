package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Platform;
import com.codecool.nerdSanctuary.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/platform")
    public List<Platform> getAllPlatform() {
        return platformService.getAllPlatform();
    }

    @GetMapping("/platform/{id}")
    public Platform getPlatform(@PathVariable long id) {
        return platformService.getPlatform(id);
    }

    @PostMapping("/platform")
    public Platform savePlatform(@Valid @RequestBody Platform platform) {
        return platformService.savePlatform(platform);
    }

    @PutMapping("/platform/{id}")
    public Platform updatePlatform(@PathVariable long id,@Valid @RequestBody Platform updatedPlatform) {
        return platformService.updatePlatform(id, updatedPlatform);
    }

    @DeleteMapping("/platform/{id}")
    public List<Platform> deletePlatform(@PathVariable long id) {
        return platformService.deletePlatform(id);
    }
}
