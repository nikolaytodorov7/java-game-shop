package ogs.model;

import ogs.dao.Identifiable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringJoiner;

public class Category implements Identifiable<Long>, Serializable {
    static final long serialVersionUID = 42L;
    private Long id;
    private String title;
    private String description;
    private Timestamp created;
    private Timestamp modified;

    public Category() {
    }

    public Category(String name, String description) {
        this.id = id;
        this.title = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(" | ", "", "")
                .add("id=" + id)
                .add("name='" + title + "'")
                .add("description='" + description + "'")
                .add("created=" + created)
                .add("modified=" + modified)
                .toString();
    }
}
