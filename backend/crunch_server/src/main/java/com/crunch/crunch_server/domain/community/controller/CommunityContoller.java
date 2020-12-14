package com.crunch.crunch_server.domain.community.controller;

import com.crunch.crunch_server.domain.community.dto.FirstCommunityBlobDTO;
import com.crunch.crunch_server.domain.community.service.CommunityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class CommunityContoller {
    
    
    @Autowired
    private CommunityService communityService;

      //get community blob
      @CrossOrigin(origins="*")
      @GetMapping("/{projectId}/index/{indexId}/CommunityBlob")
      public FirstCommunityBlobDTO makeChatCommunity(@PathVariable(value="projectId") int projectId, @PathVariable(value="indexId") int indexId)
      {
         return communityService.getCommunityBlob(projectId, indexId);
      }
}
