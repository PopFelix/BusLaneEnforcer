package ro.ubb.thesis.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubb.thesis.app.domain.VideoUpload;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoUploadRepository extends JpaRepository<VideoUpload, UUID> {
    Optional<VideoUpload> findVideoUploadsByName(String name);

}
