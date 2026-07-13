package model;

import enums.EventStatus;


public class Event {
    private Long id;
    private String location;
    private int capacity;
    private int reserved_count;
    private double ticket_price;
    private EventStatus eventStatus;


    public Event(String location, int capacity, int reserved_count, double ticket_price, EventStatus eventStatus) {
        this.location = location;
        this.capacity = capacity;
        this.reserved_count = reserved_count;
        this.ticket_price = ticket_price;
        this.eventStatus = eventStatus;
    }

    public Event(Long id, String location, int capacity, int reserved_count, double ticket_price, EventStatus eventStatus) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.reserved_count = reserved_count;
        this.ticket_price = ticket_price;
        this.eventStatus = eventStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getReserved_count() {
        return reserved_count;
    }

    public void setReserved_count(int reserved_count) {
        this.reserved_count = reserved_count;
    }

    public double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(double ticket_price) {
        this.ticket_price = ticket_price;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
