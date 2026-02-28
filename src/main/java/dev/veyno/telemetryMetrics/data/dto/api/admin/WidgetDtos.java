package dev.veyno.telemetryMetrics.data.dto.api.admin;


import java.util.UUID;

public final class WidgetDtos {

    public record CreateWidgetRequest(UUID graphId) {}
    public record UpdateWidgetRequest(UUID graphId) {}

    public record WidgetResponse(UUID id, UUID graphId) {}
}