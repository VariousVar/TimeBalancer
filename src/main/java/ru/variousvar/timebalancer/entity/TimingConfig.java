package ru.variousvar.timebalancer.entity;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Table(name = "timing_configs")
public class TimingConfig {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;

    @OneToMany
    private Profile profile;

    private Set<Timing> timings;

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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Timing> getTimings() {
        return timings;
    }

    public void setTimings(Set<Timing> timings) {
        this.timings = timings;
    }
}
