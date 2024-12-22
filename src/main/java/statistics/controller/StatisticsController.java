package statistics.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import statistics.dto.StatisticDto;
import statistics.mapper.StatisticMapper;
import statistics.service.StatisticService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController {
    private final StatisticService statisticService;
    private final StatisticMapper statisticMapper;

    @PostMapping
    public Mono<StatisticDto> createStatistic(@RequestParam String userId,
                                              @RequestParam String mathType,
                                              @RequestParam String operationType,
                                              @RequestParam Double value) {
        return statisticService.createStatistic(userId, mathType, operationType, value)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/{statisticId}")
    public Mono<StatisticDto> getStatistic(@RequestParam String userId,
                                           @PathVariable String statisticId) {
        return statisticService.getStatistic(userId, statisticId)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/by_math_type")
    public Flux<StatisticDto> getStatistics(@RequestParam String userId,
                                            @RequestParam String mathType) {
        return statisticService.getStatistics(userId, mathType)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/by_math_type/count")
    public Mono<Double> getSumStatistics(@RequestParam String userId,
                                      @RequestParam String mathType) {
        return statisticService.getSumStatistics(userId, mathType);
    }

    @GetMapping("/by_type")
    public Flux<StatisticDto> getStatistics(@RequestParam String userId,
                                            @RequestParam String operationType,
                                            @RequestParam String mathType) {
        return statisticService.getStatistics(userId, operationType, mathType)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/by_type/count")
    public Mono<Double> getSumStatistics(@RequestParam String userId,
                                         @RequestParam String operationType,
                                         @RequestParam String mathType) {
        return statisticService.getSumStatistics(userId, operationType, mathType);
    }

    @GetMapping("/by_value")
    public Flux<StatisticDto> getStatistics(@RequestParam String userId,
                                            @RequestParam String mathType,
                                            @RequestParam Double valueFrom,
                                            @RequestParam Double valueTo) {
        return statisticService.getStatistics(userId, mathType, valueFrom, valueTo)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/by_value/count")
    public Mono<Double> getSumStatistics(@RequestParam String userId,
                                         @RequestParam String mathType,
                                         @RequestParam Double valueFrom,
                                         @RequestParam Double valueTo) {
        return statisticService.getSumStatistics(userId, mathType, valueFrom, valueTo);
    }

    @GetMapping("/all")
    public Flux<StatisticDto> getAllStatistics(@RequestParam String userId) {
        return statisticService.getAllStatistics(userId)
                .map(statisticMapper::toDto);
    }

    @GetMapping("/all/count")
    public Mono<Double> getSumStatistics(@RequestParam String userId) {
        return statisticService.getSumAllStatistics(userId);
    }

    @DeleteMapping
    public Mono<String> deleteStatistics(@RequestParam String userId,
                                    @RequestParam String statisticId){
        return statisticService.deleteStatistics(userId, statisticId);
    }
}
