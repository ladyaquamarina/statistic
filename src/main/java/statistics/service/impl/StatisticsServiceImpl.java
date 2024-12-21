package statistics.service.impl;

import lombok.RequiredArgsConstructor;
import statistics.entity.StatisticsEntity;
import statistics.enums.StatisticsStatus;
import statistics.repository.StatisticsRepository;
import statistics.service.StatisticsService;
import statistics.service.InterfaceService;
import statistics.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static statistics.util.Util.UNAUTHORIZED_ACCESS_ATTEMPT_EXCEPTION;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    private final UserService userService;
    private final InterfaceService interfaceService;

    @Override
    public Mono<StatisticsEntity> sendStatistics(String interfaceId, String authorId, String statistics) {
        StatisticsEntity statisticsEntity = createNewStatistics(authorId, interfaceId, statistics);
        return checkUserMatchInterface(authorId, interfaceId)
                .map(b -> statisticsEntity)
                .flatMap(entity -> statisticsRepository.countByInterfaceId(interfaceId)
                        .map(count -> {
                            entity.setNumberInLayer(count == null ? 1 : ++count);
                            return entity;
                        }));
    }

    @Override
    public Mono<StatisticsEntity> getStatistics(String interfaceId, String userId, String statisticsId) {
        return checkUserMatchInterface(userId, interfaceId)
                .flatMap(a -> statisticsRepository.findById(statisticsId));
    }

    @Override
    public Mono<StatisticsEntity> getStatistics(String userId) {
        return checkIdFromNotUserIdList(userId)
                .filter(check -> check)
                .switchIfEmpty(Mono.error(UNAUTHORIZED_ACCESS_ATTEMPT_EXCEPTION))
                .flatMap(statisticsRepository::findById);
    }

    @Override
    public Flux<StatisticsEntity> getAllStatistics(String interfaceId, String userId) {
        return checkUserMatchInterface(userId, interfaceId)
                .flatMapMany(a -> statisticsRepository.findAllByInterfaceId(interfaceId));
    }

    @Override
    public Mono<String> deleteStatistics(String statisticsId, String userId, String interfaceId) {
        return checkUserMatchInterface(userId, interfaceId);
    }

    private Mono<Boolean> checkUserMatchInterface(String userId, String interfaceId) {
        return userService.getAllNotUserIds()
                .any(id -> Objects.equals(id, userId))
                .flatMap(checkSupportId -> interfaceService.getByIdAndUserId(interfaceId, userId)
                        .map(checkMatchInterface -> !checkSupportId || checkMatchInterface != null)
                        .switchIfEmpty(checkSupportId ? Mono.just(true) : Mono.empty()))
                .switchIfEmpty(Mono.error(UNAUTHORIZED_ACCESS_ATTEMPT_EXCEPTION))
                .map(interface -> true);
    }

    private Mono<Boolean> checkIdFromNotUserIdList(String userId) {
        return userService.getAllNotUserIds()
                .any(id -> Objects.equals(id, userId));
    }

    private StatisticsEntity createNewStatistics(String authorId, String interfaceId, String statistics) {
        StatisticsEntity entity = new StatisticsEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setAuthorId(authorId);
        entity.setLayerId(interfaceId);
        entity.setStatistics(statistics);
        entity.setStatus(StatisticsStatus.ACTIVE);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setNew(true);
        return entity;
    }
}
