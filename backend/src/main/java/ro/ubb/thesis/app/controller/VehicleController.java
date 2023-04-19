package ro.ubb.thesis.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.thesis.api.VehicleApi;
import ro.ubb.thesis.api.dto.AcceptedVehicleDto;
import ro.ubb.thesis.api.dto.UnauthorizedVehicleLogDto;
import ro.ubb.thesis.app.service.AcceptedVehicleService;
import ro.ubb.thesis.app.service.UnauthorizedVehicleLogService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VehicleController implements VehicleApi {
    private final AcceptedVehicleService acceptedVehicleService;
    private final UnauthorizedVehicleLogService logService;

    @Override
    public List<AcceptedVehicleDto> getAcceptedVehicles() {
        return acceptedVehicleService.getAcceptedVehicles();
    }

    @Override
    public AcceptedVehicleDto addAcceptedVehicle(AcceptedVehicleDto vehicleDto) {
        return acceptedVehicleService.saveVehicle(vehicleDto);
    }

    @Override
    public AcceptedVehicleDto updateAcceptedVehicle(AcceptedVehicleDto vehicleDto, UUID id) {
        return acceptedVehicleService.updateVehicle(id, vehicleDto);
    }

    @Override
    public void deleteAcceptedVehicle(UUID id) {
        acceptedVehicleService.deleteVehicle(id);
    }

    @Override
    public AcceptedVehicleDto getAcceptedVehicleByLicensePlate(String licensePlate) {
        return acceptedVehicleService.getVehicleByLicensePlate(licensePlate);
    }

    @Override
    public UnauthorizedVehicleLogDto registerUnauthorizedVehicleLog(UnauthorizedVehicleLogDto vehicleLogDto) {
        return logService.registerUnauthorizedVehicleLog(vehicleLogDto);
    }

    @Override
    public List<UnauthorizedVehicleLogDto> getAllUnauthorizedVehicles() {
        return logService.getAllUnauthorizedVehicles();
    }


}
