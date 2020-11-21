// package com.crunch.crunch_server.domain.project.controller;
package com.crunch.crunch_server.domain.project.controller;

import com.crunch.crunch_server.domain.project.dto.*;

import com.crunch.crunch_server.domain.project.entity.*;
import com.crunch.crunch_server.domain.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/project/startup")
    @ResponseStatus(value = HttpStatus.OK)
    public void addProject(@RequestHeader(value = "token") String token, @RequestBody ProjectStartDTO projectStartDTO) {
        // System.out.println(projectStartDTO.getTitle());
        // 프로젝트를 save
        System.out.println("heeloo");
        service.addProject(projectStartDTO);
    }

}
