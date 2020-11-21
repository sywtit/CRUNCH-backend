package com.crunch.crunch_server.domain.crew.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tmpbuyercrew")
public class TmpBuyerCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postindexId;
    private int projectId;
    private int userId;
}
