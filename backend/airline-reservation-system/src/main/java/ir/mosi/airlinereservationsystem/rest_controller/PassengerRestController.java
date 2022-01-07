package ir.mosi.airlinereservationsystem.rest_controller;

import ir.mosi.airlinereservationsystem.entity.Passenger;
import ir.mosi.airlinereservationsystem.exception.DuplicatePassengerException;
import ir.mosi.airlinereservationsystem.exception.PassengerNotFoundException;
import ir.mosi.airlinereservationsystem.service.PassengerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RestController
@RequestMapping("/rest/api/passenger")
public class PassengerRestController {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    CompletableFuture<ResponseEntity> findAllPassengers() {
        return passengerService.findAll().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindPassengersFailure);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) throws DuplicatePassengerException, InterruptedException, ExecutionException {

        try {
            CompletableFuture<Passenger> createdPassenger = passengerService.create(passenger);
            return ResponseEntity.ok(createdPassenger.get());
        } catch (DuplicatePassengerException e) {
            throw new DuplicatePassengerException(e.getMessage());
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger) throws PassengerNotFoundException, ExecutionException, InterruptedException {

        try {
            CompletableFuture<Passenger> updatedPassenger = passengerService.update(passenger);
            return ResponseEntity.ok(updatedPassenger.get());
        } catch (PassengerNotFoundException e) {
            throw new PassengerNotFoundException();
        }
    }

    private static Function<Throwable, ResponseEntity<? extends List<Passenger>>> handleFindPassengersFailure = throwable -> {
        //LOGGER.error("Failed to read passengers records: {}");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
