package com.example.Online.Ticketing.controller;

import com.example.Online.Ticketing.model.Event;
import com.example.Online.Ticketing.model.Row;
import com.example.Online.Ticketing.model.User;
import com.example.Online.Ticketing.service.EventService;
import com.example.Online.Ticketing.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private HallService hallService;

    // Retrieves event details by event ID, populates the model with event and logged-in user details, and returns the event-details view.
    @GetMapping("/event-details/{eventId}")
    public String getEventDetails(@PathVariable Integer eventId, Model model, HttpSession session) {
        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return "redirect:/";
        }

        model.addAttribute("event", event);

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }

        return "event-details";
    }
    // Displays the seat selection page for an event, providing how the seats are divided in rows and event details to the model.
    @GetMapping("/select-seats/{eventId}")
    public String showSelectSeatsPage(@PathVariable Integer eventId, Model model, HttpSession session) {

        Event event = eventService.getEventById(eventId);
        if (event == null) {
            return "redirect:/";
        }

        List<Row> rows = hallService.getHallLayout(event.getEventId());

        model.addAttribute("rows", rows);
        model.addAttribute("event", event);
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
        }
        return "select-seats";
    }

}