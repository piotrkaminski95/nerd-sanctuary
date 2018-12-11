package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DeveloperController {

//    TO-DO: This class!
//    @Autowired
//    DeveloperService service;

    @Autowired
    private DeveloperRepository repository;

    public void setRepository(DeveloperRepository repository) {
        this.repository = repository;
    }


    /*public void setService(DeveloperService service) {
        this.service = service;
    }
     */

    @GetMapping("/developer")
    public List<Developer> getAllDevelopers() {
        return repository.findAll();
    }
}
