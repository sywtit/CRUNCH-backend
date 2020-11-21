package com.crunch.crunch_server.domain.project.repository;

import com.crunch.crunch_server.domain.project.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findById(int id);

}
