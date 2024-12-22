package statistics.service.impl;

import lombok.RequiredArgsConstructor;
import statistics.entity.StatisticEntity;
import statistics.enums.MathType;
import statistics.enums.OperationType;
import statistics.enums.StatisticsStatus;
import statistics.repository.StatisticsRepository;
import statistics.service.StatisticService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static statistics.util.Util.SUCCESS;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticsRepository statisticsRepository;

    @Override
    public Mono<StatisticEntity> createStatistic(String userId, String mathType, String operationType, Double value) {
        StatisticEntity entity = createNewStatistics(userId, mathType, operationType, value);
        return statisticsRepository.save(entity);
    }

    @Override
    public Mono<StatisticEntity> getStatistic(String userId, String statisticId) {
        return statisticsRepository.findByIdAndUserId(statisticId, userId);
    }

    @Override
    public Flux<StatisticEntity> getStatistics(String userId, String mathType) {
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.findAllByUserIdAndMathType(userId, math);
    }

    @Override
    public Mono<Double> getSumStatistics(String userId, String mathType) {
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.sumByUserIdAndMathType(userId, math);
    }

    @Override
    public Flux<StatisticEntity> getStatistics(String userId, String operationType, String mathType) {
        OperationType operation = OperationType.valueOf(operationType);
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.findAllByUserIdAndMathTypeAndOperationType(userId, math, operation);
    }

    @Override
    public Mono<Double> getSumStatistics(String userId, String operationType, String mathType) {
        OperationType operation = OperationType.valueOf(operationType);
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.sumByUserIdAndMathTypeAndOperationType(userId, math, operation);
    }

    @Override
    public Flux<StatisticEntity> getStatistics(String userId, String mathType, Double valueFrom, Double valueTo) {
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.findAllByUserIdAndMathTypeAndValue(userId, math, valueFrom, valueTo);
    }

    @Override
    public Mono<Double> getSumStatistics(String userId, String mathType, Double valueFrom, Double valueTo) {
        MathType math = MathType.valueOf(mathType);
        return statisticsRepository.sumByUserIdAndMathTypeAndValue(userId, math, valueFrom, valueTo);
    }

    @Override
    public Flux<StatisticEntity> getAllStatistics(String userId) {
        return statisticsRepository.findAllByUserId(userId);
    }

    @Override
    public Mono<Double> getSumAllStatistics(String userId) {
        return statisticsRepository.sumByUserIdAndMathType(userId, MathType.PLUS)
                .flatMap(sumPlus -> statisticsRepository.sumByUserIdAndMathType(userId, MathType.MINUS)
                        .map(sumMinus -> sumPlus - sumMinus)
                );
    }

    @Override
    public Mono<String> deleteStatistics(String userId, String statisticId) {
        return statisticsRepository.findByIdAndUserId(statisticId, userId)
                .map(this::setDeleted)
                .flatMap(e -> statisticsRepository.save(e))
                .then(Mono.just(SUCCESS));
    }

    private StatisticEntity setDeleted(StatisticEntity entity) {
        entity.setStatus(StatisticsStatus.DELETED);
        return entity;
    }

    private StatisticEntity createNewStatistics(String userId, String mathType, String operationType, Double value) {
        StatisticEntity entity = new StatisticEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setUserId(userId);
        entity.setMathType(MathType.valueOf(mathType));
        entity.setOperationType(OperationType.valueOf(operationType));
        entity.setValue(value);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setStatus(StatisticsStatus.ACTIVE);
        entity.setNew(true);
        return entity;
    }
}
