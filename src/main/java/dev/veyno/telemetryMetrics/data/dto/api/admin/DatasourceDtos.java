package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.util.UUID;

public final class DatasourceDtos {

    public enum DatasourceType { RAW, COMPOSED }
    public enum CompositionType { ADD, AVERAGE }

    public record CreateDatasourceRequest(
            DatasourceType type,
            UUID serviceId,
            CompositionType compositionType
    ) {}

    public record UpdateDatasourceRequest(
            DatasourceType type,
            UUID serviceId,
            CompositionType compositionType
    ) {}

    public record DatasourceResponse(
            UUID id,
            DatasourceType type,
            UUID serviceId,
            CompositionType compositionType
    ) {}
}