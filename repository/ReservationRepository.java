package repository;

import enums.EventStatus;
import enums.ReservationStatus;
import exception.DatabaseRepositoryException;
import model.Reservation;
import util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationRepository implements GenericRepository <Reservation>{
    @Override
    public Long save(Reservation reservation) {
        String sql ="insert into reservation (" +
                "customer_name," +
                " customer_phone," +
                " event_id," +
                " ticket_count," +
                " reservation_date," +
                " status" +
                ")" +
                " values (?,?,?,?,?,?) returning id";
        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, reservation.getCustomerName());
            ps.setString(2, reservation.getCustomerPhone());
            ps.setLong(3, reservation.getEventId());
            ps.setInt(4, reservation.getTicketCount());
            ps.setDate(5, reservation.getReservationDate());
            ps.setString(6, ReservationStatus.ACTIVE.toString());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getLong("id");
                throw new DatabaseRepositoryException("Reservation ID Not Returned!");
            }
        }
            catch (SQLException e){
                throw new DatabaseRepositoryException("The reservation save failed... "+e.getMessage());
            }

    }



    @Override
    public boolean update(Long id, Reservation reservation) {
        String sql ="UPDATE reservations SET customer_name=?, customer_phone=?, event_id=?, ticket_count=? WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, reservation.getCustomerName());
            ps.setString(2, reservation.getCustomerPhone());
            ps.setLong(3, reservation.getEventId());
            ps.setInt(4, reservation.getTicketCount());
            ps.setLong(5, reservation.getId());

            int rowsAffected = ps.executeUpdate();

            return !(rowsAffected == 0);
        }
        catch (SQLException e) {
            throw new DatabaseRepositoryException("Updating in reservation Table Failed!" + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete reservation where id = ?";
        try(Connection connection = DatabaseConfig.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            int row = ps.executeUpdate();
            if(row==0)return false;
            return true;
        }catch (SQLException e){
            throw new DatabaseRepositoryException("The reservation delete failed... "+e.getMessage());
        }

    }

    @Override
    public Optional<Reservation> findById(Long id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(
                            new Reservation(
                                    rs.getLong(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getLong(4),
                                    rs.getInt(5),
                                    rs.getDate(6),
                                    ReservationStatus.valueOf(rs.getString(7))
                            )
                    );
                }

                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseRepositoryException("Finding By ID From reservations Table Failed!" + e.getMessage());

        }
    }

    @Override
    public boolean cancel(Long id) {

        String sql ="UPDATE reservation SET status = ? WHERE id = ?";

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
            throw new DatabaseRepositoryException("Canceling in reservation Table Failed!" + e.getMessage());
        }
    }

    @Override
    public List<Reservation> findAll() {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT * FROM reservations ORDER BY id"
             );
             ResultSet rs = ps.executeQuery()
        ) {
            List<Reservation> reservations = new ArrayList<>();
            while (rs.next()) {
                reservations.add(
                        new Reservation(
                                rs.getLong(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getLong(4),
                                rs.getInt(5),
                                rs.getDate(6),
                                ReservationStatus.valueOf(rs.getString(7))
                        )
                );
            }

            return reservations;
        }
        catch (SQLException e) {
            throw new DatabaseRepositoryException("Finding All From reservations Table Failed!" + e.getMessage());
        }
    }
}
