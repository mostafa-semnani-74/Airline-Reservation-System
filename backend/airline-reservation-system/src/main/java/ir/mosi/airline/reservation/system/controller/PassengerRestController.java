package ir.mosi.airline.reservation.system.controller;

import io.swagger.annotations.Api;
import ir.mosi.airline.reservation.system.entity.Passenger;
import ir.mosi.airline.reservation.system.exception.DuplicatePassengerException;
import ir.mosi.airline.reservation.system.service.PassengerService;
import ir.mosi.airline.reservation.system.exception.PassengerNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RestController
@RequestMapping("/rest/api/passenger")
@Api(value = "PassengerRestController")
public class PassengerRestController {

    private final PassengerService passengerService;

    public PassengerRestController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping(value = "/findAll")
    public CompletableFuture<ResponseEntity> findAllPassengers() {
        return passengerService.findAll().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindPassengersFailure);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Passenger> createPassenger(@Valid @RequestBody Passenger passenger)
            throws DuplicatePassengerException, InterruptedException, ExecutionException {
        CompletableFuture<Passenger> createdPassenger = passengerService.create(passenger);
        return ResponseEntity.ok(createdPassenger.get());
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Passenger> updatePassenger(@Valid @RequestBody Passenger passenger)
            throws PassengerNotFoundException, ExecutionException, InterruptedException {
        CompletableFuture<Passenger> updatedPassenger = passengerService.update(passenger);
        return ResponseEntity.ok(updatedPassenger.get());
    }

    private static Function<Throwable, ResponseEntity<? extends List<Passenger>>> handleFindPassengersFailure = throwable -> {
        //LOGGER.error("Failed to read passengers records: {}");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
