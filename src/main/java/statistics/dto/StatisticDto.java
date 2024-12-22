package statistics.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private String id;
    private String userId;
    private String mathType;
    private String operationType;
    private Double value;
    private LocalDateTime createdAt;
    private String status;
}