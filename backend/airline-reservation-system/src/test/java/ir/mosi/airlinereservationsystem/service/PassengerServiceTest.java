package ir.mosi.airlinereservationsystem.service;

import ir.mosi.airlinereservationsystem.entity.Passenger;
import ir.mosi.airlinereservationsystem.exception.DuplicatePassengerException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerServiceTest {

    @Autowired
    private PassengerService passengerService;

    @Test
    @Order(1)
    public void findAll_ReturnsPassengerList() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Passenger>> all = passengerService.findAll();
        Assertions.assertEquals(3, all.get().size());
    }

    @Test
    @Order(2)
    public void findByName_WithExistingPassenger_ReturnsPassenger() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Passenger>> byName = passengerService.findByName("mosi");
        Assertions.assertNotNull(byName);
        Assertions.assertEquals(byName.get().get(0).getName(), "mosi");
    }

    @Test
    @Order(3)
    public void createPassenger_ReturnsPassenger() throws InterruptedException, ExecutionException, DuplicatePassengerException {
        CompletableFuture<Passenger> passenger = passengerService.create(new Passenger("gandalf"));
        Assertions.assertEquals(passenger.get().getName(), "gandalf");
    }

}
