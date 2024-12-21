package statistics.service.impl;

import lombok.RequiredArgsConstructor;
import statistics.entity.InterfaceEntity;
import statistics.entity.StatisticsEntity;
import statistics.repository.InterfaceRepository;
import statistics.service.InterfaceService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InterfaceServiceImpl {
    private final InterfaceRepository interfaceRepository;

    @Override
    public Mono<InterfaceEntity> getById(String id) {
        return interfaceRepository.findById(id);
    }

    @Override
    public Mono<InterfaceEntity> getByIdAndUserId(String id, String userId) {
        return interfaceRepository.findByIdAndUserId(id, userId);
    }
    @Override
    public Mono<InterfaceEntity> addSupportToInterface(StatisticsEntity statistics, String supportId) {
        return interfaceRepository.findById(statistics.getInterfaceId())
                .map(interface -> {
                    interface.getSupportIds().add(supportId);
                    return interface;
                })
                .flatMap(interfaceRepository::save);
    }
}
