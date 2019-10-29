package br.com.managebot.model;

import javax.persistence.Entity;

@Entity
public class Item extends AbstractEntity{
    private String location;
    private String category;
    private String item;
    private String description;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
