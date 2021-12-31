package ir.mosi.airlinereservationsystem.service;

import ir.mosi.airlinereservationsystem.entity.Passenger;
import ir.mosi.airlinereservationsystem.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Async
    public CompletableFuture<List<Passenger>> findAll() {

        //LOGGER.info("Request to get a list of Passengers");

        final List<Passenger> passengers = passengerRepository.findAll();
        return CompletableFuture.completedFuture(passengers);
    }

}
