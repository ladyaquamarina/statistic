package statistics.service;

import statistics.entity.StatisticsEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatisticsService {
    Mono<StatisticsEntity> sendStatistics(String interfaceId, String authorId, String statistics);
    Mono<StatisticsEntity> getStatistics(String interfaceId, String userId, String statisticsId);
    Mono<StatisticsEntity> getStatistics(String userId);
    Flux<StatisticsEntity> getAllStatistics(String interfaceId, String userId);
    Mono<String> deleteStatistics(String statisticsId, String userId, String interfaceId);
}
