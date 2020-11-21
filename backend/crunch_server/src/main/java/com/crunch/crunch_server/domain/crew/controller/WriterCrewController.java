package com.crunch.crunch_server.domain.crew.controller;

import com.crunch.crunch_server.domain.crew.dto.ApplyingWriterDTO;
import com.crunch.crunch_server.domain.crew.service.BuyerCrewService;
import com.crunch.crunch_server.domain.crew.service.WriterCrewService;
import com.crunch.crunch_server.domain.project.dto.PostFeeDTO;
import com.crunch.crunch_server.domain.project.dto.PostIndexDTO;
import com.crunch.crunch_server.domain.project.service.PostService;
import com.crunch.crunch_server.domain.user.service.UserService;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WriterCrewController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private WriterCrewService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/writerapply")
    @ResponseStatus(value = HttpStatus.OK)
    public void WriterApply(@RequestHeader(value = "token") String token,
            @RequestBody ApplyingWriterDTO applyingWriterDTO) {

        //
        int userId = jwtUtil.getUserId(token);
        service.addWriterApply(userId, applyingWriterDTO.getComment());

    }

}
