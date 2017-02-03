package ru.variousvar.timebalancer.entity;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;

/**
 * Relate to concrete timing sequence. It could one of hobbies or one of working period. There is store a timing props,
 * such as duration of timing, that could be calculated and compared with real time duration spent on this occupancy.
 */
@Entity
@Table(name = "timings")
public class Timing {

    @Id
    @GeneratedValue
    private long id;

    /**
     * The name of timing.
     */
    private String name;

    /**
     *  The time unit, corresponded to occupancy.
     */
    @Enumerated(EnumType.STRING)
    private ChronoUnit unit;

    /**
     * The duration in 'unit', that occupancy may take.
     */
    private long duration;

    /**
     * Link to profile.
     */
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

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

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
