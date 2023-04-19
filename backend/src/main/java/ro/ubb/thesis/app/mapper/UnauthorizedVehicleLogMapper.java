package ro.ubb.thesis.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ro.ubb.thesis.api.dto.UnauthorizedVehicleLogDto;
import ro.ubb.thesis.app.domain.UnauthorizedVehicleLog;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UnauthorizedVehicleLogMapper {
    @Mapping(source = "externalId", target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "logDate", defaultExpression = "java(java.time.LocalDateTime.now())")
    UnauthorizedVehicleLog toEntity(UnauthorizedVehicleLogDto dto);

    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "videoUploadFileName", expression = "java(entity.getVideoUpload().getName())")
    UnauthorizedVehicleLogDto toDto(UnauthorizedVehicleLog entity);
}
