package statistics.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import statistics.dto.StatisticDto;
import statistics.entity.StatisticEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import statistics.enums.MathType;
import statistics.enums.OperationType;
import statistics.enums.StatisticsStatus;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
//    @InheritInverseConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "mathType", target = "mathType")
    @Mapping(source = "operationType", target = "operationType", qualifiedByName = "getOperationFromString")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "new", ignore = true)
    StatisticEntity toEntity(StatisticDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "mathType", target = "mathType")
    @Mapping(source = "operationType", target = "operationType", qualifiedByName = "getStringFromOperation")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "status", target = "status")
    StatisticDto toDto(StatisticEntity entity);

    @Named("getMathFromString")
    static MathType getMathFromString(String value) {
        return MathType.valueOf(value);
    }

    @Named("getOperationFromString")
    static OperationType getOperationFromString(String value) {
        return OperationType.valueOf(value);
    }

    @Named("getStatusFromString")
    static StatisticsStatus getStatusFromString(String value) {
        return StatisticsStatus.valueOf(value);
    }

    @Named("getStringFromMath")
    static String getStringFromMath(StatisticsStatus type) {
        return type.name();
    }

    @Named("getStringFromOperation")
    static String getStringFromOperation(OperationType type) {
        return type.getValue();
    }

    @Named("getStringFromStatus")
    static String getStringFromStatus(StatisticsStatus type) {
        return type.name();
    }
}
