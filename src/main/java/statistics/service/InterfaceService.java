package statistics.service;

import statistics.entity.StatisticsEntity;
import statistics.entity.InterfaceEntity;
import reactor.core.publisher.Mono;

public interface InterfaceService {
    Mono<InterfaceEntity> getById(String id);
    Mono<InterfaceEntity> getByIdAndUserId(String id, String userId);
    Mono<InterfaceEntity> addSupportToInterface(StatisticsEntity statistics, String supportId);
}
