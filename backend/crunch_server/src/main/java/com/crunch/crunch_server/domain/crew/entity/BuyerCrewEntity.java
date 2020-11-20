package com.crunch.crunch_server.domain.crew.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
// import org.springframework.data.annotation.Id;

import javax.persistence.Id;
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
@Table(name = "buyercrew")
public class BuyerCrewEntity {
    @Id
    @GeneratedValue
    private int postindexId;
    private int projectId;
    private int userId;

}
