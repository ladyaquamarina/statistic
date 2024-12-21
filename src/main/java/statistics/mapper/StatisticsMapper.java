package statistics.mapper;

import statistics.dto.StatisticsDto;
import statistics.entity.StatisticsEntity;
import statistics.enums.StatisticsStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {
    @InheritInverseConfiguration
    StatisticsEntity toEntity(StatisticsDto dto);

    StatisticsDto toDto(StatisticsEntity entity);
}
