package com.crunch.crunch_server.domain.commit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="postmodification")
public class PostModification {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="commitId")
    private Commits commits;
    
    //private int commitId;
    
    private int beforePostLength;
    private int afterPostLength;

    private String diffResult;


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Commits return the commits
     */
    public Commits getCommits() {
        return commits;
    }

    /**
     * @param commits the commits to set
     */
    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    /**
     * @return int return the beforePostLength
     */
    public int getBeforePostLength() {
        return beforePostLength;
    }

    /**
     * @param beforePostLength the beforePostLength to set
     */
    public void setBeforePostLength(int beforePostLength) {
        this.beforePostLength = beforePostLength;
    }

    /**
     * @return int return the afterPostLength
     */
    public int getAfterPostLength() {
        return afterPostLength;
    }

    /**
     * @param afterPostLength the afterPostLength to set
     */
    public void setAfterPostLength(int afterPostLength) {
        this.afterPostLength = afterPostLength;
    }

    /**
     * @return String return the diffResult
     */
    public String getDiffResult() {
        return diffResult;
    }

    /**
     * @param diffResult the diffResult to set
     */
    public void setDiffResult(String diffResult) {
        this.diffResult = diffResult;
    }

}
