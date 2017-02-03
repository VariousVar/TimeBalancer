package ru.variousvar.timebalancer.entity;

import javax.persistence.*;

/**
 * Realize a profile for many timings. It could be a work profile or all hobbies profile or something else.
 * But the idea is it unions one or few related timings.
 */
@Entity
@Table(name = "PROFILES")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The name of profile.
     */
    private String name;

    /**
     * The description of profile, describe its content.
     */
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
