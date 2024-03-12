package com.example.Online.Ticketing.controller;

import com.example.Online.Ticketing.model.Event;
import com.example.Online.Ticketing.model.User; // Import the User model
import com.example.Online.Ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final EventService eventService;

    @Autowired
    public HomeController(EventService eventService) {
        this.eventService = eventService;
    }

    // Displays the home page with events filtered by category.
    @GetMapping("/")
    public String home(@RequestParam(value = "category", defaultValue = "All") String category, Model model) {
        List<Event> events;
        if (category.equals("All")) {
            events = eventService.findAllEvents();
        } else {
            events = eventService.findEventsByCategory(category);
        }
        model.addAttribute("selectedCategory", category);
        model.addAttribute("events", events);
        model.addAttribute("user", new User());
        return "index";
    }
}