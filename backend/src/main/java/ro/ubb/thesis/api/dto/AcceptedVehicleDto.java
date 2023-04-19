package ro.ubb.thesis.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcceptedVehicleDto {
    private UUID externalId;
    @NotNull
    private String licensePlateNumber;
    private String carType;
    private String make;
    private String model;
    private LocalDate lastInspectionDate;
    @NotNull
    private String activityType;
}
