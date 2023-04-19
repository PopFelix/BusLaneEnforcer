package ro.ubb.thesis.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.thesis.api.dto.ResponseFileDto;
import ro.ubb.thesis.app.exceptions.ResponseMessage;

import java.util.List;

@RequestMapping("/video")
public interface VideoApi {
    @PostMapping("/upload")
    ResponseEntity<ResponseMessage> uploadVideo(@RequestParam MultipartFile file);

    @GetMapping("/unauthorized-logs/{licensePlate}")
    ResponseEntity<List<ResponseFileDto>> getAllVideosForLicensePlate(@PathVariable String licensePlate);

    @GetMapping("/{id}")
    ResponseEntity<byte[]> getFile(@PathVariable String id);
}
