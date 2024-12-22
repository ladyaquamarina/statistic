package statistics.repository;

import org.springframework.data.r2dbc.repository.Query;
import statistics.entity.StatisticEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import statistics.enums.MathType;
import statistics.enums.OperationType;

@Repository
public interface StatisticsRepository extends R2dbcRepository<StatisticEntity, String> {
    Mono<StatisticEntity> findByIdAndUserId(String id, String userId);
    Flux<StatisticEntity> findAllByUserIdAndMathType(String userId, MathType mathType);

    @Query("""
        SELECT sum(value)
        FROM statistic
        WHERE user_id = :userId
        AND math_type = :mathType
    """)
    Mono<Double> sumByUserIdAndMathType(String userId, MathType mathType);
    Flux<StatisticEntity> findAllByUserIdAndMathTypeAndOperationType(String userId, MathType mathType, OperationType operationType);

    @Query("""
        SELECT sum(value)
        FROM statistic
        WHERE user_id = :userId
        AND math_type = :mathType
        AND operation_type = :operationType
    """)
    Mono<Double> sumByUserIdAndMathTypeAndOperationType(String userId, MathType mathType, OperationType operationType);

    @Query("""
        SELECT *
        FROM statistic
        WHERE user_id = :userId
        AND math_type = :mathType
        AND value < :valueTo
        AND value > :valueFrom
    """)
    Flux<StatisticEntity> findAllByUserIdAndMathTypeAndValue(String userId, MathType mathType, Double valueFrom, Double valueTo);

    @Query("""
        SELECT sum(value)
        FROM statistic
        WHERE user_id = :userId
        AND math_type = :mathType
        AND value < :valueTo
        AND value > :valueFrom
    """)
    Mono<Double> sumByUserIdAndMathTypeAndValue(String userId, MathType mathType, Double valueFrom, Double valueTo);
    Flux<StatisticEntity> findAllByUserId(String userId);

//    @Query("""
//        UPDATE statistic
//        SET status = 'DELETED'
//        WHERE id = :id
//        AND user_id = :userId
//    """)
//    Mono<Void> deleteById(String userId, String id);
}
