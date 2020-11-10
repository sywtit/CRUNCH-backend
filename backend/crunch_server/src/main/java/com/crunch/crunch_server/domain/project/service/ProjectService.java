package com.crunch.crunch_server.domain.project.service;

// import javax.print.event.PrintJobAdapter;

import com.crunch.crunch_server.domain.project.dto.ProjectTitleDdayDTO;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.project.mapper.ProjectTitleDdayMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository repository;

    // first main page
    public ProjectTitleDdayDTO getProjectTitleDday(int projectId)
    {
        Project project = repository.findById(projectId).orElse(null);
        // String projectTitle = project.getTitle();
        // String projectDday = project.getTarget_d_day();

        return ProjectTitleDdayMapper.Instance.toProjectTitleDdayDTO(project);
    }
  
}
