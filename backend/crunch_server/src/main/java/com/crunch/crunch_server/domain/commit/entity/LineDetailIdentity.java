package com.crunch.crunch_server.domain.commit.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LineDetailIdentity implements Serializable
{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    PostModification postModification;
    private int lineNumber;

  
    /**
     * @return int return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

}
