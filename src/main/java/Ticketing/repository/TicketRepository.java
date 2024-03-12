package com.example.Online.Ticketing.repository;

import com.example.Online.Ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketByClient_UserId(Integer userId);

}