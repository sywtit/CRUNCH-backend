package com.crunch.crunch_server.domain.project.controller;

import java.util.List;

import com.crunch.crunch_server.domain.crew.dto.WriterListDTO;
import com.crunch.crunch_server.domain.crew.dto.WriterMoneyPercentDTO;
import com.crunch.crunch_server.domain.crew.service.WriterCrewService;
import com.crunch.crunch_server.domain.project.dto.CompletedPostListDTO;
import com.crunch.crunch_server.domain.project.dto.ContentsReadingDTO;
import com.crunch.crunch_server.domain.project.dto.GenreDTO;
import com.crunch.crunch_server.domain.project.dto.MyWritingDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIdDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectIndexUserDTO;
import com.crunch.crunch_server.domain.project.dto.ProjectStartDTO;
import com.crunch.crunch_server.domain.project.dto.ReadingIndexListDTO;
import com.crunch.crunch_server.domain.project.dto.RecruitingProjectListDTO;
import com.crunch.crunch_server.domain.project.dto.SetIndexFeeDTO;
import com.crunch.crunch_server.domain.project.dto.TmpDTO;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.service.*;
import com.crunch.crunch_server.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContentsController {
    @Autowired
    private ProjectService service;

    @Autowired
    private WriterCrewService writerCrewservice;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "*")
    @PostMapping("/getContentsReading")
    @ResponseStatus(value = HttpStatus.OK)
    public ContentsReadingDTO getContents(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIndexUserDTO pDto) {

        int userId = jwtUtil.getUserId(token);

        return service.getContentsOfProjectId(pDto.getProjectId(), pDto.getPostIndex());

    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getContentsReadingIndexList")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ReadingIndexListDTO> getContents(@RequestHeader(value = "token") String token,
            @RequestBody ProjectIdDTO projectIdDTO) {

        int userId = jwtUtil.getUserId(token);

        return service.getContentsIndexListOfProjectId(projectIdDTO.getId());

    }
}
