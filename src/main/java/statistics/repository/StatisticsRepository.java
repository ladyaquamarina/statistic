package statistics.repository;

import statistics.entity.StatisticsEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StatisticsRepository extends R2dbcRepository<StatisticsEntity, String> {
    Mono<Integer> countByInterfaceId(String interfaceId);
    Flux<StatisticsEntity> findAllByInterfaceId(String interfaceId);

    @Query("""
        UPDATE statistics
        SET status = 'DELETED'
        WHERE id = :id
    """)
    Mono<Void> deleteById(String id);
}
