package statistics.entity;

import java.time.LocalDateTime;

import statistics.enums.MathType;
import statistics.enums.OperationType;
import statistics.enums.StatisticsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("statistic")
public class StatisticEntity implements Persistable<String> {
    @Id
    @Column("id")
    private String id;

    @Column("user_id")
    private String userId;

    @Column("math_type")
    private MathType mathType;

    @Column("operation_type")
    private OperationType operationType;

    @Column("value")
    private Double value;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private StatisticsStatus status;

    @Transient
    private boolean isNew = false;
}
