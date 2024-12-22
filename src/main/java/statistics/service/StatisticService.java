package statistics.service;

import statistics.entity.StatisticEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatisticService {
    Mono<StatisticEntity> createStatistic(String userId, String mathType, String operationType, Double value);
    Mono<StatisticEntity> getStatistic(String userId, String statisticId);
    Flux<StatisticEntity> getStatistics(String userId, String mathType);
    Mono<Double> getSumStatistics(String userId, String mathType);
    Flux<StatisticEntity> getStatistics(String userId, String operationType, String mathType);
    Mono<Double> getSumStatistics(String userId, String operationType, String mathType);
    Flux<StatisticEntity> getStatistics(String userId, String mathType, Double valueFrom, Double valueTo);
    Mono<Double> getSumStatistics(String userId, String mathType, Double valueFrom, Double valueTo);
    Flux<StatisticEntity> getAllStatistics(String userId);
    Mono<Double> getSumAllStatistics(String userId);
    Mono<String> deleteStatistics(String userId, String statisticId);
}
