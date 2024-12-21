package statistics.entity;

import java.time.LocalDateTime;
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
@Table("statistics")
public class StatisticsEntity implements Persistable<String> {
    @Id
    @Column("id")
    private String id;

    @Column("layer_id")
    private String layerId;

    @Column("author_id")
    private String authorId;

    @Column("statistics")
    private String statistics;

    @Column("number_in_layer")
    private Integer numberInLayer;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("status")
    private StatisticsStatus status;

    @Transient
    private boolean isNew = false;
}
