package com.example.Online.Ticketing.utility;

import java.io.Serializable;
import java.util.Objects;

public class HallMapPK implements Serializable {

    private Integer event;

    private Integer seatNumber;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallMapPK that)) return false;
        return Objects.equals(event, that.event) &&
                Objects.equals(seatNumber, that.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, seatNumber);
    }
}