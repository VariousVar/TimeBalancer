package ru.variousvar.timebalancer.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "time_mark")
public class TimeMark {

    public interface CommonView {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Timing timing;

    @JsonView(CommonView.class)
    private Instant mark;

    @JsonView(CommonView.class)
    private String description;

    @JsonView(CommonView.class)
    private Boolean start;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timing getTiming() {
        return timing;
    }

    public void setTiming(Timing timing) {
        this.timing = timing;
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

    public Boolean getStart() {
        return start;
    }

    public void setStart(Boolean start) {
        this.start = start;
    }
}
