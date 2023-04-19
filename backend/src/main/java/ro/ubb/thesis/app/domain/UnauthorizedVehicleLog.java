package ro.ubb.thesis.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "unauthorized_vehicle_logs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UnauthorizedVehicleLog extends BaseEntity {
    private String licensePlateNumber;

    private LocalDateTime logDate;

    @ManyToOne
    @JoinColumn(name = "video_upload_id")
    private VideoUpload videoUpload;
}
