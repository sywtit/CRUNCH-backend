package com.crunch.crunch_server.domain.commit.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="linedetail")
public class LineDetail {
    
    @EmbeddedId
    private LineDetailIdentity linedetailIdentity;

    private String type;
    private int isEmpty;
    private String s3key;
 


    /**
     * @return LineDetailIdentity return the linedetailIdentity
     */
    public LineDetailIdentity getLinedetailIdentity() {
        return linedetailIdentity;
    }

    /**
     * @param linedetailIdentity the linedetailIdentity to set
     */
    public void setLinedetailIdentity(LineDetailIdentity linedetailIdentity) {
        this.linedetailIdentity = linedetailIdentity;
    }

    /**
     * @return String return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return int return the isEmpty
     */
    public int getIsEmpty() {
        return isEmpty;
    }

    /**
     * @param isEmpty the isEmpty to set
     */
    public void setIsEmpty(int isEmpty) {
        this.isEmpty = isEmpty;
    }

  
    /**
     * @return String return the s3key
     */
    public String getS3key() {
        return s3key;
    }

    /**
     * @param s3key the s3key to set
     */
    public void setS3key(String s3key) {
        this.s3key = s3key;
    }

}
