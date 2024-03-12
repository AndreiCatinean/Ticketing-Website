package com.example.Online.Ticketing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    private Integer seatNumber;
    private Double payment;

    @ManyToOne
    @JoinColumn(name = "Event_ID", updatable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "Client_ID", updatable = false)
    private User client;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }


    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double totalPayment) {
        this.payment = totalPayment;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }


}
