package ir.mosi.airlinereservationsystem.service;

import ir.mosi.airlinereservationsystem.entity.Passenger;
import ir.mosi.airlinereservationsystem.exception.DuplicatePassengerException;
import ir.mosi.airlinereservationsystem.exception.PassengerNotFoundException;
import ir.mosi.airlinereservationsystem.repository.PassengerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Async
    public CompletableFuture<List<Passenger>> findAll() {

        //LOGGER.info("Request to get a list of Passengers");

        final List<Passenger> passengers = passengerRepository.findAll();
        return CompletableFuture.completedFuture(passengers);
    }

    @Async
    public CompletableFuture<Passenger> create(Passenger passenger) throws ExecutionException, InterruptedException, DuplicatePassengerException {
        CompletableFuture<List<Passenger>> passengerInDB = findByName(passenger.getName());

        if (passengerInDB.get().size() == 0)
            passengerRepository.save(passenger);
        else
            throw new DuplicatePassengerException("Passenger already exist with id : " + passengerInDB.get().get(0).getId());

        return CompletableFuture.completedFuture(passenger);
    }

    @Async
    public CompletableFuture<Passenger> update(Passenger passenger) throws ExecutionException, InterruptedException, PassengerNotFoundException {
        CompletableFuture<List<Passenger>> passengerForUpdate = findByName(passenger.getName());

        if (passengerForUpdate.get().size() == 1) {
            passengerForUpdate.get().get(0).setName(passenger.getName());
            passengerRepository.save(passengerForUpdate.get().get(0));
            return CompletableFuture.completedFuture(passenger);
        } else {
            throw new PassengerNotFoundException("Passenger not found");
        }
    }

    @Async
    public CompletableFuture<List<Passenger>> findByName(String name) {
        final List<Passenger> passengers = passengerRepository.findByName(name);
        return CompletableFuture.completedFuture(passengers);
    }


}
