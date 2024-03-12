package com.example.Online.Ticketing.controller;


import com.example.Online.Ticketing.model.Ticket;
import com.example.Online.Ticketing.model.User;
import com.example.Online.Ticketing.service.EventService;
import com.example.Online.Ticketing.service.TicketService;
import com.example.Online.Ticketing.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    // Registers a new user, checks for existing email, and returns appropriate response.
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> registerUser(@ModelAttribute User user) {
        if (userService.emailExists(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Email already in use."));
        }
        userService.registerUser(user);
        return ResponseEntity
                .ok(Map.of("success", true, "message", "Registration successful!"));
    }

    // Handles user login, sets user in session if credentials are valid, and returns appropriate response.
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
        User user = userService.loginUser(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok().body(Map.of("success", true, "message", "Login successful!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "Invalid credentials"));
        }
    }

    // Displays a user's tickets, requires user to be logged in.
    @GetMapping("/my-tickets")
    public String viewUserTickets(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }

        List<Ticket> tickets = ticketService.getTicketsByUserId(loggedInUser.getUserId());
        for (Ticket t : tickets)
            t.setEvent(eventService.getEventById(t.getEvent().getEventId()));
        model.addAttribute("tickets", tickets);
        return "user-tickets";
    }

    // Checks session status and returns logged-in user details or logged-out status.
    @GetMapping("/sessionStatus")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> sessionStatus(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("userId", loggedInUser.getUserId());
            userMap.put("lastName", loggedInUser.getLastName());
            userMap.put("firstName", loggedInUser.getFirstName());
            userMap.put("email", loggedInUser.getEmail());
            userMap.put("phoneNumber", loggedInUser.getPhoneNumber());


            Map<String, Object> response = new HashMap<>();
            response.put("isLoggedIn", true);
            response.put("user", userMap);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(Map.of("isLoggedIn", false));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }


}