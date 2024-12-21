package statistics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatisticsStatus {
    ACTIVE("Активно"),
    DELETED("Удалено");

    private final String value;
}
