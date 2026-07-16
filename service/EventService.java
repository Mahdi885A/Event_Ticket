package service;

import model.Event;
import repository.GenericRepository;

import java.util.List;
import java.util.Optional;

public class EventService implements GenericRepository<Event> {

    @Override
    public Long save(Event event) {
        return 0L;
    }

    @Override
    public boolean update(Long id, Event event) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean cancel(Long id) {
        return false;
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }
}
