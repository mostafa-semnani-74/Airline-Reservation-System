package ir.mosi.airline.reservation.system.service;

import ir.mosi.airline.reservation.system.entity.Ticket;
import ir.mosi.airline.reservation.system.repository.TicketRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Async
    public CompletableFuture<List<Ticket>> findAll() {

        //LOGGER.info("Request to get a list of Tickets");

        final List<Ticket> tickets = ticketRepository.findAll();
        return CompletableFuture.completedFuture(tickets);
    }

    @Async
    public CompletableFuture<List<Ticket>> findAllAvailableTickets() {

        //LOGGER.info("Request to get a list of vailableTickets");

        final List<Ticket> tickets = ticketRepository.findAllAvailableTickets();
        return CompletableFuture.completedFuture(tickets);
    }

    @Async
    public CompletableFuture<Ticket> updatePassengerName(Ticket ticket) throws Exception {
        final Ticket ticketForUpdate = ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new RuntimeException("No ticket found with id"+ticket.getId()));

        ticketForUpdate.setPassengerName(ticket.getPassengerName());
        ticketRepository.save(ticketForUpdate);
        return CompletableFuture.completedFuture(ticket);

    }
}
