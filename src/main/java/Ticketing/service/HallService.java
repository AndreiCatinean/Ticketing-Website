package com.example.Online.Ticketing.service;

import com.example.Online.Ticketing.model.HallMap;
import com.example.Online.Ticketing.model.Row;
import com.example.Online.Ticketing.model.Seat;
import com.example.Online.Ticketing.repository.HallMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallService {
    private final HallMapRepository hallMapRepository;

    @Autowired
    public HallService(HallMapRepository hallMapRepository) {
        this.hallMapRepository = hallMapRepository;
    }

    // Generates a layout of the hall for a specific event, organizing seats into rows.
    public List<Row> getHallLayout(Integer eventId) {

        List<HallMap> hallMaps = hallMapRepository.findByEvent_EventId(eventId);

        int capacity = (int) Math.ceil(Math.sqrt(hallMaps.size()));

        List<Row> rows = new ArrayList<>();
        Row currentRow = new Row();
        int currentSeatInRow = 0;

        for (HallMap hallMap : hallMaps) {
            if (currentSeatInRow == capacity) {
                rows.add(currentRow);
                currentRow = new Row();
                currentSeatInRow = 0;
            }

            Seat seat = new Seat();
            seat.setEventId(hallMap.getEvent().getEventId());
            seat.setSeatNumber(hallMap.getSeatNumber());
            seat.setStatus(hallMap.getStatus());
            seat.setPrice(hallMap.getPrice());

            currentRow.getSeats().add(seat);
            currentSeatInRow++;

        }


        if (!currentRow.getSeats().isEmpty()) {
            rows.add(currentRow);
        }

        return rows;
    }
}