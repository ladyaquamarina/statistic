package statistics.service.impl;

import lombok.RequiredArgsConstructor;
import statistics.repository.UserRepository;
import statistics.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;

    @Override
    public Flux<String> getAllNotUserIds() {
        return userRepository.getAllNotUserId();
    }
}
