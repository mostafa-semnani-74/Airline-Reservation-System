package ir.mosi.airlinereservationsystem.rest_controller;

import ir.mosi.airlinereservationsystem.entity.Airplane;
import ir.mosi.airlinereservationsystem.exception.AirplaneNotFoundException;
import ir.mosi.airlinereservationsystem.exception.DuplicateAirplaneException;
import ir.mosi.airlinereservationsystem.service.AirplaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;


@RestController
@RequestMapping("/rest/api/airplane")
public class AirplaneRestController {

    private final AirplaneService airplaneService;

    public AirplaneRestController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping("/findAll")
    public CompletableFuture<ResponseEntity> findAllAirplanes() {
        return airplaneService.findAll().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindAirplanesFailure);
    }

    @PostMapping("/create")
    public ResponseEntity<Airplane> createAirplane(@Valid @RequestBody Airplane airplane) throws InterruptedException, ExecutionException, DuplicateAirplaneException {
        CompletableFuture<Airplane> createdAirplane = airplaneService.create(airplane);
        return ResponseEntity.ok(createdAirplane.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Airplane> updateAirplane(@Valid @RequestBody Airplane airplane) throws ExecutionException, InterruptedException, AirplaneNotFoundException {
        CompletableFuture<Airplane> updatedAirplane = airplaneService.update(airplane);
        return ResponseEntity.ok(updatedAirplane.get());
    }

    private static Function<Throwable, ResponseEntity<? extends List<Airplane>>> handleFindAirplanesFailure = throwable -> {
        //LOGGER.error("Failed to read passengers records: {}");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
