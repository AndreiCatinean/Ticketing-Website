package com.example.Online.Ticketing.service;

import com.example.Online.Ticketing.model.Ticket;
import com.example.Online.Ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUserId(Integer userId) {
        return ticketRepository.findTicketByClient_UserId(userId);
    }
}
