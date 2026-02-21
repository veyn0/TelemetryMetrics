package xyz.herweg.telemtryMetrics.data.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(
        name = "metric_samples",
        indexes = {
                @Index(name = "idx_metric_samples_service_ts", columnList = "service_id,timestamp")
        }
)
public class MetricSample {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "service_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID serviceId;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "value", nullable = false)
    private long value;

    protected MetricSample() {}

    public MetricSample(UUID id, UUID serviceId, long timestamp, long value) {
        this.id = id;
        this.serviceId = serviceId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public UUID getId() { return id; }
    public UUID getServiceId() { return serviceId; }
    public long getTimestamp() { return timestamp; }
    public long getValue() { return value; }
}
