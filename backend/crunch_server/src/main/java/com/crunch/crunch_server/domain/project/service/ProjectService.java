package com.crunch.crunch_server.domain.project.service;

import java.util.List;

import com.crunch.crunch_server.domain.project.dto.ProjectStartDTO;

// import javax.print.event.PrintJobAdapter;

import com.crunch.crunch_server.domain.project.dto.ProjectTitleDdayDTO;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.project.entity.Tag;
import com.crunch.crunch_server.domain.project.entity.TagIdentity;
import com.crunch.crunch_server.domain.project.repository.ProjectRepository;
import com.crunch.crunch_server.domain.project.repository.TagRepository;
import com.crunch.crunch_server.domain.project.mapper.ProjectTitleDdayMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TagRepository tagRepository;

    // first main page
    public ProjectTitleDdayDTO getProjectTitleDday(int projectId) {
        Project project = repository.findById(projectId).orElse(null);
        // String projectTitle = project.getTitle();
        // String projectDday = project.getTarget_d_day();

        return ProjectTitleDdayMapper.Instance.toProjectTitleDdayDTO(project);
    }

    public int addProject(ProjectStartDTO projectStartDTO) {

        Project project = new Project();
        project.setTitle(projectStartDTO.getTitle());
        project.setIntroduction(projectStartDTO.getIntroduction());
        project.setMwn(projectStartDTO.getMwn());
        project.setTarget_d_day(projectStartDTO.getTarget_d_day());
        project.setTarget_funding_money(projectStartDTO.getTarget_funding_money());

        int projectId = repository.save(project).getId();
        System.out.println("----------------gettag------------------");
        System.out.println(projectStartDTO.getTags());
        // for (String dtoTag : projectStartDTO.getTags()) {
        for (int i = 0; i < projectStartDTO.getTags().size(); i++) {
            Tag tagEntity = new Tag();
            TagIdentity tIdentity = new TagIdentity();
            tIdentity.setProjectId(projectId);
            tagEntity.setTagIdentity(tIdentity);
            tagEntity.setText(projectStartDTO.getTags().get(i));
            tagRepository.save(tagEntity);

        }

        return projectId;
    }

    public ProjectStartDTO getRecruitingProjectInfo(int id) {
        Project project = repository.findById(id);
        ProjectStartDTO projectStartDTO = new ProjectStartDTO();

        projectStartDTO.setTitle(project.getTitle());
        projectStartDTO.setIntroduction(project.getIntroduction());
        projectStartDTO.setMwn(project.getMwn());
        projectStartDTO.setTarget_d_day(project.getTarget_d_day());
        projectStartDTO.setTarget_funding_money(project.getTarget_funding_money());

        return projectStartDTO;
    }

    public void changeProjectStateWriting(int projectId) {
        System.out.println("------------");
        System.out.println(projectId);
        Project project = repository.findById(projectId);
        project.setState("writing");

        repository.save(project);

    }

}
