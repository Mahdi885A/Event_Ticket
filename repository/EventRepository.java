package repository;

import enums.EventStatus;
import exception.DatabaseRepositoryException;
import model.Event;
import util.DatabaseConfig;

import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepository implements GenericRepository<Event> {
    @Override
    public Long save(Event event){
        String sql = "insert into event (title, location, capacity, reserved_count, ticket_price, status) values (?,?,?,?,?,?) returning id";
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,event.getTitle());
            ps.setString(2,event.getLocation());
            ps.setInt(3,event.getCapacity());
            ps.setInt(4,event.getReservedCount());
            ps.setDouble(5,event.getTicketPrice());
            ps.setString(6,event.getEventStatus().toString());
            ResultSet rs = ps.executeQuery();
            try (rs){
                if (rs.next()){
                    return rs.getLong("id");
                }
                throw new DatabaseRepositoryException("The event was not saved. ");
            }


        } catch (SQLException e) {
            throw new DatabaseRepositoryException("The event save failed... "+e.getMessage());
        }

    }

    @Override
    public boolean update(Long id,Event event) {
        String sql ="update events set title=?, location=?, capacity=?, ticket_price=? where id = ?";
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, event.getTitle());
            ps.setString(2, event.getLocation());
            ps.setInt(3, event.getCapacity());
            ps.setDouble(4, event.getTicketPrice());
            ps.setLong(5,id);

            int row = ps.executeUpdate();
            if(row==0)return false;
            return true;

        }catch (SQLException e){
            throw new DatabaseRepositoryException("The event update failed... "+e.getMessage());
        }

    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete event where id = ?";
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            int row = ps.executeUpdate();
            if(row==0)return false;
            return true;
        }catch (SQLException e){
            throw new DatabaseRepositoryException("The event delete failed... "+e.getMessage());
        }

    }

    @Override
    public Optional<Event> findById(Long id) {
        String sql = "select * from event where id =?";
        try (Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);

            ResultSet rs =ps.executeQuery();
            try(rs){
                if (rs.next()){
                    return Optional.of(new Event(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getDouble(6),
                            EventStatus.valueOf(rs.getString(7))
                            ));
                }
            }
            return Optional.empty();

        }catch (SQLException e){
            throw new DatabaseRepositoryException("Find ID failed"+e.getMessage());
        }
    }

    @Override
    public boolean cancel(Long id) {
        String sql ="UPDATE events SET status = ? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, EventStatus.CANCELLED.toString());
            ps.setLong(2, id);

            int row = ps.executeUpdate();


             if(row == 0){
                 return false;
             }
             return true;
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("Canceling in events Table Failed!" + e.getMessage());
        }
    }

    @Override
    public List<Event> findAll() {
        String sql = "SELECT * FROM events";

        try (Connection connection = DatabaseConfig.getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Event> events = new ArrayList<>();
            while (rs.next()) {
                events.add(
                        new Event(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4),
                                rs.getInt(5),
                                rs.getDouble(6),
                                EventStatus.valueOf(rs.getString(7))
                        )
                );
            }

            return events;
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("Finding All From events Table Failed..." + e.getMessage());
        }
    }


}
