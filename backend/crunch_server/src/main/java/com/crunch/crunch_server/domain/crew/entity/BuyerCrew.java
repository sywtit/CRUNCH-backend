package com.crunch.crunch_server.domain.crew.entity;

import java.io.Serializable;

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

    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "postindex_projectId", referencedColumnName = "projectId"),
            @JoinColumn(name = "postindex_id", referencedColumnName = "id") })
    private PostIndex postIndex;
    // @ManyToOne
    // @JoinColumn(name = "postindex_projectId", referencedColumnName = "projectId")
    // private PostIndex postIndexProjectId;

    // @OneToOne
    // @JoinColumn(name = "postindex_id", referencedColumnName = "id")
    // private PostIndex postIndex;
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // @EmbeddedId
    // private BuyerCrewIdentity buyerCrewIdentity;

    // @Id
    // @GeneratedValue
    // private int postindexId;
    // private int projectId;
    // private int userId;

    // // @Column(name = "projectId", insertable = false, updatable = false)
    // // @ManyToOne(cascade = CascadeType.ALL)
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id", insertable = false, updatable = false)
    // private Project project;

    // // @Column(name = "userId", insertable = false, updatable = false)
    // // @ManyToOne(cascade = CascadeType.ALL)
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id", insertable = false, updatable = false)
    // private User user;

    // // @Column(name = "postindexId", insertable = false, updatable = false)
    // // @OneToOne(cascade = CascadeType.ALL)
    // @OneToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id", insertable = false, updatable = false)
    // private PostIndex postIndex;

}
