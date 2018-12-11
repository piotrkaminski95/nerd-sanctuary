package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeveloperController {

//    TO-DO: This class!
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

}
