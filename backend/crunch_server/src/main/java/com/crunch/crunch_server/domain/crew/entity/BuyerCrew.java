package com.crunch.crunch_server.domain.crew.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.crunch.crunch_server.domain.commit.entity.LineDetailIdentity;
import com.crunch.crunch_server.domain.project.entity.PostIndex;
import com.crunch.crunch_server.domain.project.entity.Project;
import com.crunch.crunch_server.domain.user.entity.User;
import javax.persistence.EmbeddedId;
// import org.springframework.data.annotation.Id;

import javax.persistence.Id;
import javax.persistence.IdClass;

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
@IdClass(BuyerCrew.class)
public class BuyerCrew implements Serializable {

    @EmbeddedId
    private BuyerCrewIdentity buyerCrewIdentity;

    private Date purchaseDate;

}
