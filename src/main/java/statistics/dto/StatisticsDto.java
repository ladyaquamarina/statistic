package statistics.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {
    private String id;
    private String layerId;
    private String authorId;
    private String statistics;
    private Integer numberInLayer;
    LocalDateTime createdAt;
    String status;
}