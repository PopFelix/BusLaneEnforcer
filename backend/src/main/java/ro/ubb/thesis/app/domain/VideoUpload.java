package ro.ubb.thesis.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

@Entity(name = "video_uploads")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VideoUpload extends BaseEntity{
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
