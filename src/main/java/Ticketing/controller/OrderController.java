package com.example.Online.Ticketing.controller;

import com.example.Online.Ticketing.model.User;
import com.example.Online.Ticketing.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Processes order confirmation, checks if the user is logged in, and processes the order with the orderService.
    @PostMapping("/confirmOrder")
    public ResponseEntity<?> confirmOrder(@RequestBody OrderRequest orderRequest, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false, "message", "User not logged in."));
        }

        boolean result = orderService.processOrder(
                loggedInUser,
                orderRequest.getEventId(),
                orderRequest.getSeatIds()

        );
        if (result) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Order confirmed!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success", false, "message", "Could not process order."));
        }
    }


    public static class OrderRequest {
        private Integer eventId;
        private List<Integer> seatIds;


        public Integer getEventId() {
            return eventId;
        }

        public void setEventId(Integer eventId) {
            this.eventId = eventId;
        }

        public List<Integer> getSeatIds() {
            return seatIds;
        }

        public void setSeatIds(List<Integer> seatIds) {
            this.seatIds = seatIds;
        }


    }
}