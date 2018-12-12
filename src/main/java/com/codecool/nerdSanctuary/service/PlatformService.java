package com.codecool.nerdSanctuary.service;

import com.codecool.nerdSanctuary.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository repository;
}
