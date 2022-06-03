package ogs.model;

import ogs.dao.Identifiable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringJoiner;

public class Game implements Identifiable<Long>, Serializable {
    static final long serialVersionUID = 42L;
    private Long id;
    private Category category;
    private String title;
    private Seller seller;
    private String description;
    private double price;
    private Timestamp created;
    private Timestamp modified;

    public Game () {
    }

    public Game(Category category, String title, Seller seller, String description, double price) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.seller = seller;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return id.equals(game.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(" | ", "", "")
                .add("id=" + id)
                .add("category='" + category.getTitle() + "'")
                .add("title='" + title + "'")
                .add("seller='" + seller.getUsername() + "'")
                .add("description='" + description + "'")
                .add("created=" + created)
                .add("modified=" + modified)
                .add(String.format("Price: $%.2f", price / 100))
                .toString();
    }
}
