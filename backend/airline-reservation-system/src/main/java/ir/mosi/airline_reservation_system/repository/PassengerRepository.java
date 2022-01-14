package ir.mosi.airline_reservation_system.repository;

import ir.mosi.airline_reservation_system.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByName(String name);

}
