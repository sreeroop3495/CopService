package com.project.continental.CopService.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Violations {

    /**
     * This class is used to save violations in database
     */
    int id;
    @Id
    String section;
    String description;
    int penalty;

    public Violations(int id, String section, String decription, int penalty) {

        this.id = id;
        this.section = section;
        this.description = decription;
        this.penalty = penalty;
    }
    public Violations(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}
