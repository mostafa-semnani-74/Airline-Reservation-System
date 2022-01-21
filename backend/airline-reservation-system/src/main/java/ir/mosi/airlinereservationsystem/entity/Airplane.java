package ir.mosi.airlinereservationsystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "airplane")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_airplane")
    @SequenceGenerator(name = "seq_airplane", sequenceName = "seq_airplane", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long Id;

    @NotNull
    @Column(name = "model", nullable = false, unique = true)
    private String model;

    @NotNull
    @Column(name = "seat_count", nullable = false)
    private String seatCount;


    public Airplane() {
    }

    public Airplane(String model, String seatCount) {
        this.model = model;
        this.seatCount = seatCount;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

}
