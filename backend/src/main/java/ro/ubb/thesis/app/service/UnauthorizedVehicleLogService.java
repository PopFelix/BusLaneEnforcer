package ro.ubb.thesis.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.ubb.thesis.api.dto.ResponseFileDto;
import ro.ubb.thesis.api.dto.UnauthorizedVehicleLogDto;
import ro.ubb.thesis.app.exceptions.VideoNotFoundException;
import ro.ubb.thesis.app.mapper.UnauthorizedVehicleLogMapper;
import ro.ubb.thesis.app.repo.UnauthorizedVehicleLogRepository;
import ro.ubb.thesis.app.repo.VideoUploadRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnauthorizedVehicleLogService {
    private final UnauthorizedVehicleLogRepository logRepository;
    private final UnauthorizedVehicleLogMapper logMapper;
    private final VideoUploadRepository videoUploadRepository;

    @Transactional
    public UnauthorizedVehicleLogDto registerUnauthorizedVehicleLog(UnauthorizedVehicleLogDto dto) {
        var videoUpload = videoUploadRepository.findVideoUploadsByName(dto.getVideoUploadFileName());
        if (videoUpload.isEmpty()) {
            throw new VideoNotFoundException("Video upload " + dto.getVideoUploadFileName() + " not found!");
        }
        var vehicleLog = logMapper.toEntity(dto);
        vehicleLog.setVideoUpload(videoUpload.get());
        return logMapper.toDto(logRepository.save(vehicleLog));
    }

    @Transactional
    public List<ResponseFileDto> getAllVideoUploadsForLicensePlate(String licensePlate) {
        var videoUploads = logRepository.findVideoUploadsByLicensePlateNumber(licensePlate);
        if (videoUploads.isEmpty()) {
            throw new VideoNotFoundException("Video upload for license plate" + licensePlate + " not found!");
        }
        return videoUploads.stream()
                .map(videoUpload -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/video/")
                            .path(videoUpload.getName())
                            .toUriString();
                    return new ResponseFileDto(videoUpload.getName(),
                            fileDownloadUri,
                            videoUpload.getType(),
                            videoUpload.getData().length);
                })
                .collect(Collectors.toList());
    }

    public List<UnauthorizedVehicleLogDto> getAllUnauthorizedVehicles() {
        return logRepository.findAll()
                .stream()
                .map(logMapper::toDto)
                .collect(Collectors.toList());
    }
}
