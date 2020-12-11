package com.crunch.crunch_server.domain.project.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class TagIdentity implements Serializable {
    private int projectId;
}
