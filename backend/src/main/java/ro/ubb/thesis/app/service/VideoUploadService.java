package ro.ubb.thesis.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.thesis.app.domain.VideoUpload;
import ro.ubb.thesis.app.repo.VideoUploadRepository;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VideoUploadService {
    private final VideoUploadRepository videoUploadRepository;

    public VideoUpload uploadVideoFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        VideoUpload video = new VideoUpload(fileName, file.getContentType(), file.getBytes());

        return videoUploadRepository.save(video);
    }

    @Transactional
    public VideoUpload getFile(String id) {
        return videoUploadRepository.findVideoUploadsByName(id).get();
    }

    public Stream<VideoUpload> getAllFiles() {
        return videoUploadRepository.findAll().stream();
    }
}
