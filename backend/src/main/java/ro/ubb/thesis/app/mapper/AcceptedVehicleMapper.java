package ro.ubb.thesis.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import ro.ubb.thesis.api.dto.AcceptedVehicleDto;
import ro.ubb.thesis.app.domain.AcceptedVehicle;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface AcceptedVehicleMapper {
    @Mapping(source = "externalId",target = "id", defaultExpression = "java(java.util.UUID.randomUUID())")
    AcceptedVehicle toEntity(AcceptedVehicleDto dto);

    @Mapping(source = "id",target = "externalId")
    AcceptedVehicleDto toDto(AcceptedVehicle vehicle);
}
