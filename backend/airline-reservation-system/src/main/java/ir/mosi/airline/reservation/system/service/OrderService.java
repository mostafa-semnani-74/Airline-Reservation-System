package ir.mosi.airline.reservation.system.service;

import ir.mosi.airline.reservation.system.entity.Ticket;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private final TicketService ticketService;

    public OrderService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Async
    public CompletableFuture<List<Ticket>> findAllAvailableTickets() {
        return ticketService.findAllAvailableTickets();
    }

    @Async
    public CompletableFuture<Ticket> orderTicket(Ticket ticket) throws Exception {
        return ticketService.updatePassengerName(ticket);
    }
}
