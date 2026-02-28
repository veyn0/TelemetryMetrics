package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.util.UUID;

public final class TooltipDtos {

    public record CreateTooltipRequest(UUID widgetId, UUID graphId) {}

    public record TooltipResponse(UUID id, UUID widgetId, UUID graphId) {}
}