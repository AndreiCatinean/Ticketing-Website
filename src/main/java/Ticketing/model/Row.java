package com.example.Online.Ticketing.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private final List<Seat> seats;


    public Row() {
        seats = new ArrayList<>();
    }

    public List<Seat> getSeats() {
        return seats;
    }


}