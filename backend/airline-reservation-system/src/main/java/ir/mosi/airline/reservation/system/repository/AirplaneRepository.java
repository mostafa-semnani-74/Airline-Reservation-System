package ir.mosi.airline.reservation.system.repository;

import ir.mosi.airline.reservation.system.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findByModel(String model);

}
