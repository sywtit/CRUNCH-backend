package com.crunch.crunch_server.domain.project.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PostIndexIdentity implements Serializable {
    private int id;
    private int projectId;
}
