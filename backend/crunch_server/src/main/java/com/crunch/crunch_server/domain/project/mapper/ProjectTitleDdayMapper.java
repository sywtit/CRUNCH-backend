package com.crunch.crunch_server.domain.project.mapper;

import com.crunch.crunch_server.domain.project.dto.ProjectTitleDdayDTO;
import com.crunch.crunch_server.domain.project.entity.Project;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectTitleDdayMapper {
    
    ProjectTitleDdayMapper Instance = Mappers.getMapper(ProjectTitleDdayMapper.class);

    ProjectTitleDdayDTO toProjectTitleDdayDTO(Project project);
    
}
