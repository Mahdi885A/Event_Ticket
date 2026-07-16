package service;

import enums.EventStatus;
import enums.ReservationStatus;
import exception.*;
import model.Event;
import model.Reservation;
import repository.EventRepository;
import repository.ReservationRepository;

import java.util.List;

public class ReservationService implements GenericService<Reservation>{
    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;

    public ReservationService(ReservationRepository reservationRepository, EventRepository eventRepository) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Long register(Reservation reservation) {
        Event event = eventRepository.findById(reservation.getEventId())
                .orElseThrow(()-> new EventNotFoundException("Event not found!"));

        if (event.getEventStatus()== EventStatus.CANCELLED){
            throw new EventCancelException("The Event Canceled!");
        }
        int remainingCapacity = event.getCapacity() - event.getReservedCount();

        if (remainingCapacity<reservation.getTicketCount()){
            throw new CapacityExceededException("Capacity not enough");
        }

        reservationRepository.save(reservation);

        event.setReservedCount(event.getReservedCount() + reservation.getTicketCount());

        eventRepository.update(event.getId(),event);
        return event.getId();
    }

    @Override
    public Reservation change(Reservation reservation) {
        Reservation reservation1 = reservationRepository.findById(reservation.getEventId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: "
                        + reservation.getEventId()));


        if (reservation.getReservationStatus() == ReservationStatus.CANCELLED) {
            throw new InvalidOperationException("Reservation is already cancelled.");
        }

        return null;
    }

    @Override
    public Long cancel(Long id) {
        return 0L;
    }

    @Override
    public Reservation getById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        return List.of();
    }
}
