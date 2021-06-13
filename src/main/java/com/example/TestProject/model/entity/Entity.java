package com.example.TestProject.model.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 *
 * @author T.Zholob
 */
public abstract class Entity  {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
