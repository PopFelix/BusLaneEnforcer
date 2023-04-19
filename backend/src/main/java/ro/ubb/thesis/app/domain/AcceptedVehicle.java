package ro.ubb.thesis.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "accepted_vehicles")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AcceptedVehicle extends BaseEntity {
    @Column(unique = true)
    private String licensePlateNumber;
    private String carType;
    private String make;
    private String model;
    private LocalDate lastInspectionDate;
    private String activityType;
}
