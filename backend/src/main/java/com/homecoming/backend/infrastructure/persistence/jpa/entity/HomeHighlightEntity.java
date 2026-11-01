package com.homecoming.backend.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "home_highlights")
public class HomeHighlightEntity {

    public static final short SINGLETON_KEY = 1;

    @Id
    @Column(name = "singleton_id", nullable = false)
    private Short singletonId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 128)
    private String dates;

    protected HomeHighlightEntity() {}

    public Short getSingletonId() {
        return singletonId;
    }

    public String getTitle() {
        return title;
    }

    public String getDates() {
        return dates;
    }
}
