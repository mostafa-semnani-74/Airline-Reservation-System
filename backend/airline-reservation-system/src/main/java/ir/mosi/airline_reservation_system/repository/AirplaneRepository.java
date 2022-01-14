package ir.mosi.airline_reservation_system.repository;

import ir.mosi.airline_reservation_system.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findByModel(String model);

}
