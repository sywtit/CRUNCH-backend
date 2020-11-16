package com.crunch.crunch_server.domain.crew.controller;

// import com.crunch.crunch_server.domain.crew.dto.BuyerCrewDTO;
import com.crunch.crunch_server.domain.project.dto.PostIndexDTO;
import com.crunch.crunch_server.domain.crew.entity.BuyerCrew;
import com.crunch.crunch_server.domain.crew.service.BuyerCrewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class BuyerCrewController {

    // private PostIndexDTO postIndexDTO;

    @CrossOrigin(origins = "*")
    @PostMapping("/user/buyContents")
    @ResponseStatus(value = HttpStatus.OK)
    public void buyContent(@RequestBody PostIndexDTO postIndexDTO) {
        System.out.println(postIndexDTO.getId());
        System.out.println(postIndexDTO.getProjectId());
    }
}
