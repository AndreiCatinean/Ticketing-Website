package com.example.Online.Ticketing.repository;

import com.example.Online.Ticketing.model.HallMap;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallMapRepository extends JpaRepository<HallMap, Integer> {


    List<HallMap> findByEvent_EventId(Integer eventId);

    Optional<HallMap> findByEvent_EventIdAndSeatNumber(Integer eventId, Integer seatId);
}