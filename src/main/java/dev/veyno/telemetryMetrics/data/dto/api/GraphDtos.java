package dev.veyno.telemetryMetrics.data.dto.api;

import java.util.UUID;

public final class GraphDtos {

    public record CreateGraphRequest(UUID datasourceId, String name) {}
    public record UpdateGraphRequest(UUID datasourceId, String name) {}

    public record GraphResponse(UUID id, UUID datasourceId, String name) {}
}