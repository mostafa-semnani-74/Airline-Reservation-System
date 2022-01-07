package ir.mosi.airlinereservationsystem.service;

import ir.mosi.airlinereservationsystem.entity.Airplane;
import ir.mosi.airlinereservationsystem.exception.AirplaneNotFoundException;
import ir.mosi.airlinereservationsystem.exception.DuplicateAirplaneException;
import ir.mosi.airlinereservationsystem.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Async
    public CompletableFuture<List<Airplane>> findAll() {
        final List<Airplane> airplanes = airplaneRepository.findAll();
        return CompletableFuture.completedFuture(airplanes);
    }

    @Async
    public CompletableFuture<Airplane> create(Airplane airplane) throws ExecutionException, InterruptedException, DuplicateAirplaneException {
        CompletableFuture<List<Airplane>> airplanesInDB = findByModel(airplane.getModel());

        if (airplanesInDB.get().size() == 0)
            airplaneRepository.save(airplane);
        else
            throw new DuplicateAirplaneException("Airplane already exist with id : " + airplanesInDB.get().get(0).getId());

        return CompletableFuture.completedFuture(airplane);
    }

    @Async
    public CompletableFuture<Airplane> update(Airplane airplane) throws ExecutionException, InterruptedException, AirplaneNotFoundException {
        CompletableFuture<List<Airplane>> airplaneForUpdate = findByModel(airplane.getModel());

        if (airplaneForUpdate.get().size() == 1) {
            airplaneForUpdate.get().get(0).setModel(airplane.getModel());
            airplaneForUpdate.get().get(0).setSeatCount(airplane.getSeatCount());
            airplaneRepository.save(airplaneForUpdate.get().get(0));
            return CompletableFuture.completedFuture(airplane);
        } else {
            throw new AirplaneNotFoundException();
        }
    }

    @Async
    public CompletableFuture<List<Airplane>> findByModel(String model) {
        final List<Airplane> airplanes = airplaneRepository.findByModel(model);
        return CompletableFuture.completedFuture(airplanes);
    }

}
