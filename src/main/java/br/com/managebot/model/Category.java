package br.com.managebot.model;

import javax.persistence.Entity;

@Entity
public class Category extends AbstractCategory{
    private String category;
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
