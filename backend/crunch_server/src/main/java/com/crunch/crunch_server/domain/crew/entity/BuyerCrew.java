package com.crunch.crunch_server.domain.crew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "buyercrew")
public class BuyerCrew {

    @Id
    @GeneratedValue

    @Column(name = "postindexId", insertable = false, updatable = false)
    private int postindexId;

    @Column(name = "projectId", insertable = false, updatable = false)
    private int projectId;

    @Column(name = "userId", insertable = false, updatable = false)
    private int userId;
}
