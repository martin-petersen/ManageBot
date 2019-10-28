package br.com.managebot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Item {
    private int id;
    private String location;
    private String category;
    private String item;
    public static List<Item> itemList;

    static {
        itemRepository();
    }

    public Item(int id, String location, String category, String item) {
        this.id = id;
        this.location = location;
        this.category = category;
        this.item = item;
    }

    public Item() {
    }

    private static void itemRepository() {
        itemList = new ArrayList<>(asList(new Item(1,"A309","Eletronico","Computador"),
                new Item(2,"A309","Eletronico","Projetor")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
