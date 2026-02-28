package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.util.UUID;

public final class DatasourceCompositionDtos {

    public record CreateCompositionRequest(UUID datasourceId, UUID serviceId) {}

    public record CompositionResponse(UUID id, UUID datasourceId, UUID serviceId) {}
}