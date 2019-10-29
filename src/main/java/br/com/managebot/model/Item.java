package br.com.managebot.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

@Entity
public class Item extends AbstractEntity{
    private String location;
    private String category;
    private String item;


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
}
