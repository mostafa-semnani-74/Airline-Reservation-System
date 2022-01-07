package ir.mosi.airlinereservationsystem.rest_controller;

import ir.mosi.airlinereservationsystem.entity.Airplane;
import ir.mosi.airlinereservationsystem.exception.AirplaneNotFoundException;
import ir.mosi.airlinereservationsystem.exception.DuplicateAirplaneException;
import ir.mosi.airlinereservationsystem.service.AirplaneService;
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
@RequestMapping("/rest/api/airplane")
public class AirplaneRestController {

    @Autowired
    private AirplaneService airplaneService;

    @RequestMapping(value = "findAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    CompletableFuture<ResponseEntity> findAllAirplanes() {
        return airplaneService.findAll().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindAirplanesFailure);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Airplane> createAirplane(@RequestBody Airplane airplane) throws InterruptedException, ExecutionException, DuplicateAirplaneException {

        try {
            CompletableFuture<Airplane> createdAirplane = airplaneService.create(airplane);
            return ResponseEntity.ok(createdAirplane.get());
        } catch (DuplicateAirplaneException e) {
            throw new DuplicateAirplaneException(e.getMessage());
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Airplane> updateAirplane(@RequestBody Airplane airplane) throws ExecutionException, InterruptedException, AirplaneNotFoundException {

        try {
            CompletableFuture<Airplane> updatedAirplane = airplaneService.update(airplane);
            return ResponseEntity.ok(updatedAirplane.get());
        } catch (AirplaneNotFoundException e) {
            throw new AirplaneNotFoundException();
        }
    }

    private static Function<Throwable, ResponseEntity<? extends List<Airplane>>> handleFindAirplanesFailure = throwable -> {
        //LOGGER.error("Failed to read Airplanes records: {}");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
