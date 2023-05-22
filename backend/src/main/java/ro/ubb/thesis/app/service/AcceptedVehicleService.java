package ro.ubb.thesis.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.thesis.api.dto.AcceptedVehicleDto;
import ro.ubb.thesis.app.domain.AcceptedVehicle;
import ro.ubb.thesis.app.exceptions.LicensePlateDuplicateException;
import ro.ubb.thesis.app.exceptions.VehicleNotFoundException;
import ro.ubb.thesis.app.mapper.AcceptedVehicleMapper;
import ro.ubb.thesis.app.repo.AcceptedVehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcceptedVehicleService {
    private final AcceptedVehicleRepository acceptedVehicleRepository;
    private final AcceptedVehicleMapper acceptedVehicleMapper;

    public AcceptedVehicleDto saveVehicle(AcceptedVehicleDto dto) throws LicensePlateDuplicateException {
        var vehicle = acceptedVehicleMapper.toEntity(dto);
        if (verifyLicensePlateDuplicate(vehicle.getLicensePlateNumber())) {
            throw new LicensePlateDuplicateException("License plate " + vehicle.getLicensePlateNumber() + " already exists!");
        }
        return acceptedVehicleMapper.toDto(acceptedVehicleRepository.save(vehicle));
    }

    @Transactional
    public AcceptedVehicleDto updateVehicle(UUID uuid, AcceptedVehicleDto dto) throws VehicleNotFoundException, LicensePlateDuplicateException {
        var vehicle = acceptedVehicleMapper.toEntity(dto);
        Optional<AcceptedVehicle> foundVehicleOptional = acceptedVehicleRepository.findById(uuid);
        if (foundVehicleOptional.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with serial id " + uuid + " was not found");
        }
        AcceptedVehicle foundVehicle = foundVehicleOptional.get();
        foundVehicle.setCarType(vehicle.getCarType());
        foundVehicle.setLastInspectionDate(vehicle.getLastInspectionDate());
        foundVehicle.setMake(vehicle.getMake());
        foundVehicle.setModel(vehicle.getModel());
        foundVehicle.setLicensePlateNumber(vehicle.getLicensePlateNumber());
        foundVehicle.setActivityType(vehicle.getActivityType());
        return acceptedVehicleMapper.toDto(acceptedVehicleRepository.save(foundVehicle));
    }

    public void deleteVehicle(UUID uuid) {
        Optional<AcceptedVehicle> foundVehicleOptional = acceptedVehicleRepository.findById(uuid);
        if (foundVehicleOptional.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with serial id " + uuid + " was not found");
        }
        acceptedVehicleRepository.deleteById(uuid);
    }

    public AcceptedVehicleDto getVehicleByLicensePlate(String licensePlate) {
        if (!acceptedVehicleRepository.existsAcceptedVehicleByLicensePlateNumber(licensePlate)) {
            throw new VehicleNotFoundException("Could not find vehicle with license plate " + licensePlate);
        }
        return acceptedVehicleMapper.toDto(acceptedVehicleRepository.getAcceptedVehicleByLicensePlateNumber(licensePlate));
    }

    public List<AcceptedVehicleDto> getAcceptedVehicles() {
        return acceptedVehicleRepository.findAll().stream()
                .map(acceptedVehicleMapper::toDto)
                .collect(Collectors.toList());
    }

    private boolean verifyLicensePlateDuplicate(String licensePlate) throws LicensePlateDuplicateException {
        return acceptedVehicleRepository.existsAcceptedVehicleByLicensePlateNumber(licensePlate);
    }
}
