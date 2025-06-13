package com.kiet.EventManagementPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kiet.EventManagementPortal.Event;
import com.kiet.EventManagementPortal.EventRepository;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return eventRepository.findById(id);
    }

    @GetMapping("/events/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByDateAfter(LocalDate.now());
    }

    @PostMapping("/event")
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/event/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event event = eventRepository.findById(id).orElseThrow();
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setVenue(eventDetails.getVenue());
        event.setDescription(eventDetails.getDescription());
        return eventRepository.save(event);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
    }

}