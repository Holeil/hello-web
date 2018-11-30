package com.start.web.domain;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}