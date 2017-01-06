package ru.variousvar.timebalancer.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "time_mark")
public class TimeMark {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private Instant mark;

    private String description;

    private Boolean stopMark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getMark() {
        return mark;
    }

    public void setMark(Instant mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStopMark() {
        return stopMark;
    }

    public void setStopMark(Boolean stopMark) {
        this.stopMark = stopMark;
    }
}
