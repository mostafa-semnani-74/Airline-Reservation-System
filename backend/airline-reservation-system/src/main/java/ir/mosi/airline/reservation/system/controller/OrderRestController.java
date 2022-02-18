package ir.mosi.airline.reservation.system.controller;

import io.swagger.annotations.Api;
import ir.mosi.airline.reservation.system.entity.Passenger;
import ir.mosi.airline.reservation.system.entity.Ticket;
import ir.mosi.airline.reservation.system.exception.DuplicatePassengerException;
import ir.mosi.airline.reservation.system.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RestController
@RequestMapping("/rest/api/order")
@Api(value = "OrderRestController")
public class OrderRestController {

    private final OrderService orderService;


    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/findAllAvailableTickets")
    public CompletableFuture<ResponseEntity> findAllAvailableTickets() {
        return orderService.findAllAvailableTickets().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleFindAllAvailableTicketsFailure);
    }

    @PostMapping(value = "/orderTicket")
    public ResponseEntity<Ticket> orderTicket(@Valid @RequestBody Ticket ticket) throws Exception {
        CompletableFuture<Ticket> orderTicket = orderService.orderTicket(ticket);
        return ResponseEntity.ok(orderTicket.get());
    }

    private static Function<Throwable, ResponseEntity<? extends List<Passenger>>> handleFindAllAvailableTicketsFailure = throwable -> {
        //LOGGER.error("Failed to read Available Tickets records: {}");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
}
