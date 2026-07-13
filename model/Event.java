package model;

import enums.EventStatus;


public class Event {
    private Long id;
    private String location;
    private int capacity;
    private int reservedCount;
    private double ticketPrice;
    private EventStatus eventStatus;


    public Event(String location, int capacity, int reserved_count, double ticket_price, EventStatus eventStatus) {
        this.location = location;
        this.capacity = capacity;
        this.reservedCount = reserved_count;
        this.ticketPrice = ticket_price;
        this.eventStatus = eventStatus;
    }

    public Event(Long id, String location, int capacity, int reserved_count, double ticket_price, EventStatus eventStatus) {
        this.id = id;
        this.location = location;
        this.capacity = capacity;
        this.reservedCount = reserved_count;
        this.ticketPrice = ticket_price;
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

    public int getReservedCount() {
        return reservedCount;
    }

    public void setReservedCount(int reservedCount) {
        this.reservedCount = reservedCount;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
