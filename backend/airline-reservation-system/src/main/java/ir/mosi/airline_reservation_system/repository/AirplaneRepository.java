package ir.mosi.airlinereservationsystem.repository;

import ir.mosi.airlinereservationsystem.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    List<Airplane> findByModel(String model);

}
