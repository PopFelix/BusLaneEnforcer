package ro.ubb.thesis.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.thesis.app.domain.UnauthorizedVehicleLog;
import ro.ubb.thesis.app.domain.VideoUpload;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnauthorizedVehicleLogRepository extends JpaRepository<UnauthorizedVehicleLog, UUID> {
    @Query("select videoUpload from unauthorized_vehicle_logs where licensePlateNumber = ?1")
    Optional<VideoUpload> findVideoUploadsByLicensePlateNumber(String licensePlate);
}
