package com.example.Online.Ticketing.service;

import com.example.Online.Ticketing.model.Event;
import com.example.Online.Ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findEventsByCategory(String category) {
        return eventRepository.findEventsByCategory(category);
    }


    public Event getEventById(Integer eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);


        return eventOptional.orElse(null);
    }


}