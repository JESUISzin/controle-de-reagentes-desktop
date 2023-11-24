package com.controledereagentes.models;

public class StatusModel {
    private Integer id;
    private String status;

    public StatusModel(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public Integer getId() {
        return id;
    }
}
