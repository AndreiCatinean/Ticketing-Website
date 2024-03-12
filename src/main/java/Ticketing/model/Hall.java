package com.example.Online.Ticketing.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hallId;
    private String hallName;
    private Integer capacity;


    @OneToMany(mappedBy = "hall")
    private List<Event> events;

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


}
