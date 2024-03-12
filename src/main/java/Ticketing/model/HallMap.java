package com.example.Online.Ticketing.model;

import com.example.Online.Ticketing.utility.HallMapPK;
import jakarta.persistence.*;

@Entity
@IdClass(HallMapPK.class)
@Table(name = "Hall_Map")
public class HallMap {


    @Id
    private Integer seatNumber;

    private String status;
    private Double price;

    @Id
    @ManyToOne
    @JoinColumn(name = "Event_ID")
    private Event event;


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


}