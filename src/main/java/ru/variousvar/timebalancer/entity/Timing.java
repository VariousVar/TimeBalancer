package ru.variousvar.timebalancer.entity;

import javax.persistence.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "timings")
public class Timing {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private ChronoUnit unit;
    private Duration duration;

    @ManyToOne
    private TimingConfig timingConfig;

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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public TimingConfig getTimingConfig() {
        return timingConfig;
    }

    public void setTimingConfig(TimingConfig timingConfig) {
        this.timingConfig = timingConfig;
    }
}
