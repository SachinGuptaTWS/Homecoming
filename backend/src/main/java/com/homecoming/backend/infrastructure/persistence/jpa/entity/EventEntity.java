package com.homecoming.backend.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @Column(length = 64, nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(name = "schedule_label", nullable = false, length = 128)
    private String scheduleLabel;

    @Column(nullable = false)
    private String location;

    protected EventEntity() {}

    public EventEntity(String id, String title, String scheduleLabel, String location) {
        this.id = id;
        this.title = title;
        this.scheduleLabel = scheduleLabel;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getScheduleLabel() {
        return scheduleLabel;
    }

    public String getLocation() {
        return location;
    }
}
