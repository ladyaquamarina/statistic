package statistics.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import statistics.dto.StatisticsDto;
import statistics.mapper.StatisticsMapper;
import statistics.service.StatisticsService;
import statistics.util.Util;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@Slf4j
public class StatisticsControllerImpl {
    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper;

    @PostMapping
    public Mono<String> sendStatistics(@RequestParam String interfaceId,
                                  @RequestParam String authorId,
                                  @RequestBody String statistics) {
        return statisticsService.sendStatistics(interfaceId, authorId, statistics)
                .map(entity -> Util.SUCCESS);
    }

    @GetMapping("/to_show")
    public Mono<StatisticsDto> getStatistics(@RequestParam String interfaceId,
                                   @RequestParam String userId,
                                   @RequestParam String statisticsId) {
        return statisticsService.getStatistics(interfaceId, userId, statisticsId)
                .map(statisticsMapper::toDto);
    }

    @GetMapping("/to_send")
    public Mono<StatisticsDto> getStatistics(@RequestParam String userId) {
        return statisticsService.getStatistics(userId)
                .map(statisticsMapper::toDto);
    }

    @GetMapping("/all")
    public Flux<StatisticsDto> getAllStatistics(@RequestParam String interfaceId,
                                    @RequestParam String userId) {
        return statisticsService.getAllStatistics(interfaceId, userId)
                .map(statisticsMapper::toDto);
    }

    @DeleteMapping
    public Mono<String> deleteStatistics(@RequestParam String statisticsId,
                                    @RequestParam String userId,
                                    @RequestParam String interfaceId){
        return statisticsService.deleteStatistics(statisticsId, userId, interfaceId);
    }
}
