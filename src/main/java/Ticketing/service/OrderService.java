package com.example.Online.Ticketing.service;

import com.example.Online.Ticketing.model.HallMap;
import com.example.Online.Ticketing.model.Ticket;
import com.example.Online.Ticketing.model.User;
import com.example.Online.Ticketing.repository.EventRepository;
import com.example.Online.Ticketing.repository.HallMapRepository;
import com.example.Online.Ticketing.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HallMapRepository hallMapRepository;

    @Autowired
    private EventRepository eventRepository;


    // Processes an order. It books seats and creates tickets
    @Transactional
    public boolean processOrder(User user, Integer eventId, List<Integer> seatIds) {
        double totalPayment = 0;

        for (Integer seatId : seatIds) {

            HallMap seat = hallMapRepository.findByEvent_EventIdAndSeatNumber(eventId, seatId).orElseThrow(() -> new RuntimeException("Seat not found"));

            if ("Occupied".equals(seat.getStatus())) {
                throw new RuntimeException("Seat is already booked");
            }

            totalPayment += seat.getPrice();

            seat.setStatus("Occupied");
            hallMapRepository.save(seat);

            Ticket ticket = new Ticket();
            ticket.setEvent(eventRepository.getById(eventId));
            ticket.setClient(user);
            ticket.setSeatNumber(seat.getSeatNumber());
            ticket.setPayment(seat.getPrice());
            ticketRepository.save(ticket);
        }


        return true;
    }
}