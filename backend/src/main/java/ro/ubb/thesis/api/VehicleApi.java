package ro.ubb.thesis.api;

import org.springframework.web.bind.annotation.*;
import ro.ubb.thesis.api.dto.AcceptedVehicleDto;
import ro.ubb.thesis.api.dto.UnauthorizedVehicleLogDto;

import java.util.List;
import java.util.UUID;

@RequestMapping("/vehicle")
public interface VehicleApi {
    @GetMapping("/accepted")
    List<AcceptedVehicleDto> getAcceptedVehicles();

    @PostMapping("/accepted")
    AcceptedVehicleDto addAcceptedVehicle(@RequestBody AcceptedVehicleDto vehicleDto);

    @PutMapping("/accepted/{id}")
    AcceptedVehicleDto updateAcceptedVehicle(@RequestBody AcceptedVehicleDto vehicleDto, @PathVariable UUID id);

    @DeleteMapping("/accepted/{id}")
    void deleteAcceptedVehicle(@PathVariable UUID id);

    @GetMapping("/accepted/{licensePlate}")
    AcceptedVehicleDto getAcceptedVehicleByLicensePlate(@PathVariable String licensePlate);

    @PostMapping("/unauthorized/")
    UnauthorizedVehicleLogDto registerUnauthorizedVehicleLog(@RequestBody UnauthorizedVehicleLogDto vehicleLogDto);

    @GetMapping("/unauthorized")
    List<UnauthorizedVehicleLogDto> getAllUnauthorizedVehicles();
}
