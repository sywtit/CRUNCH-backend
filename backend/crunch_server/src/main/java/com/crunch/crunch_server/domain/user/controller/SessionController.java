package com.crunch.crunch_server.domain.user.controller;

//#region import
import java.net.URI;
import java.net.URISyntaxException;

import com.crunch.crunch_server.domain.user.Role;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.dto.SessionResponseDTO;
import com.crunch.crunch_server.domain.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//#endregion import

@RestController
@RequestMapping("/api")
public class SessionController {
    @Autowired
    private UserService service;

    //login
    @PostMapping("/user/account/auth")
    public ResponseEntity<SessionResponseDTO> loginUser(@RequestBody SessionRequestDTO sessionRequestDTO) throws URISyntaxException
    {
        String token = null;

        try
        {
            token = service.createToken(sessionRequestDTO);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return ResponseEntity.created(new URI("/user/account/auth")).body(SessionResponseDTO
            .builder()
            .role(Role.MEMBER)
            .accessToken(token)
            .build());
    }
    
}
