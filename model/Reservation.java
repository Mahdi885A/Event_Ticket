package model;

import enums.ReservationStatus;

import java.time.LocalDate;


public class Reservation {
    private Long id;
    private String customerName;
    private Long customerPhone;
    private int ticketCount;
    private LocalDate reservation;
    private ReservationStatus reservationStatus;

    public Reservation(String customerName, Long customerPhone, int ticketCount, LocalDate reservation, ReservationStatus reservationStatus) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.ticketCount = ticketCount;
        this.reservation = reservation;
        this.reservationStatus = reservationStatus;
    }

    public Reservation(Long id, String customerName, Long customerPhone, int ticketCount, LocalDate reservation, ReservationStatus reservationStatus) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.ticketCount = ticketCount;
        this.reservation = reservation;
        this.reservationStatus = reservationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(Long customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public LocalDate getReservation() {
        return reservation;
    }

    public void setReservation(LocalDate reservation) {
        this.reservation = reservation;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
