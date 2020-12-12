package com.crunch.crunch_server.domain.project.repository;

import java.util.List;

import com.crunch.crunch_server.domain.project.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findById(int id);

	Project findByIdAndState(int intValue, String string);

	// List<Project> findByUserId(int userId);



}
