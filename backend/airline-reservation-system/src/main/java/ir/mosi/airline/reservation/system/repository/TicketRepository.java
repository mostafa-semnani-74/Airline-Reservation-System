package ir.mosi.airline.reservation.system.repository;

import ir.mosi.airline.reservation.system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t " +
            "FROM Ticket t " +
            "WHERE t.passengerName IS NULL")
    List<Ticket> findAllAvailableTickets();
}
