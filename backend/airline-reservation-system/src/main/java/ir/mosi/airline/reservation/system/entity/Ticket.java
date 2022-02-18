package ir.mosi.airline.reservation.system.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
    @SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "passenger_name", nullable = false)
    private String passengerName;

    @NotNull
    @Column(name = "from", nullable = false)
    private String from;

    @NotNull
    @Column(name = "to", nullable = false)
    private String to;

    public Ticket() {
    }

    public Ticket(Long id, String passengerName, String from, String to) {
        Id = id;
        this.passengerName = passengerName;
        this.from = from;
        this.to = to;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
