package com.crunch.crunch_server.domain.commit.service;

import com.crunch.crunch_server.domain.commit.repository.BlobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlobService {
    
    @Autowired
    private BlobRepository repository;
    
}
