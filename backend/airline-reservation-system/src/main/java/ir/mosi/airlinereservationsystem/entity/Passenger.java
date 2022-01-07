package ir.mosi.airlinereservationsystem.entity;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_passenger")
    @SequenceGenerator(name = "seq_passenger", sequenceName = "seq_passenger", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long Id;


    public Passenger() {
    }

    @Column(name = "name", nullable = false)
    private String name;


    public Passenger(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
