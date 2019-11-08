package br.com.managebot.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Location extends AbstractLocation{
    @Column(unique = true)
    private String location;
    private String description;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
