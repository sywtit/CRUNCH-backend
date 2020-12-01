package com.crunch.crunch_server.domain.crew.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BuyerCrewIdentity implements Serializable {
    private int postindexId;
    private int projectId;
    private int userId;
}
