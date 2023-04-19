package ro.ubb.thesis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnauthorizedVehicleLogDto {
    private UUID externalId;
    private String licensePlateNumber;
    private LocalDateTime logDate;
    private String videoUploadFileName;
}
