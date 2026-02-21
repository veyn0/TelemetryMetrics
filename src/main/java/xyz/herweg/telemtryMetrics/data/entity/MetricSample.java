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
    private double timestamp;

    @Column(name = "value", nullable = false)
    private double value;

    protected MetricSample() {}

    public MetricSample(UUID id, UUID serviceId, double timestamp, double value) {
        this.id = id;
        this.serviceId = serviceId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public UUID getId() { return id; }
    public UUID getServiceId() { return serviceId; }
    public double getTimestamp() { return timestamp; }
    public double getValue() { return value; }
}
