package ro.ubb.thesis.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.thesis.app.domain.AcceptedVehicle;

import java.util.UUID;

@Repository
public interface AcceptedVehicleRepository extends JpaRepository<AcceptedVehicle, UUID> {
    public AcceptedVehicle getAcceptedVehicleByLicensePlateNumber(String licensePlateNumber);
    public boolean existsAcceptedVehicleByLicensePlateNumber(String licensePlateNumber);
}
