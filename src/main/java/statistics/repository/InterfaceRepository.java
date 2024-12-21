package statistics.repository;

import statistics.entity.InterfaceEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceRepository extends R2dbcRepository<InterfaceEntity, String> {
    Mono<InterfaceEntity> findByIdAndUserId(String id, String userId);
}
