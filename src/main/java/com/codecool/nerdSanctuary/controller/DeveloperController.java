package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
    // i don't know but i have idea to send here some graphical content it will be nicer
//    @GetMapping("/developer/all")
    @GetMapping("/developer")
    public List<Developer> getAllDevelopers() {
        return repository.findAll();
    }


    @GetMapping("/developer/{id}")
    public Developer getDeveloper(@PathVariable long id) {
        if (repository.exists(id)) {
            return repository.findOne(id);
        }
        throw new IllegalArgumentException(String.format("ID = %s does not exist!"));
//        return service.getDeveloper(id);
    }
}
