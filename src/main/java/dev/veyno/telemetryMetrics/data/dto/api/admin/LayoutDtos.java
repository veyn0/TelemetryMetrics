package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.time.Instant;
import java.util.UUID;

public final class LayoutDtos {

    public record CreateLayoutRequest(
            UUID widgetId,
            UUID statusPageId,
            int posX,
            int posY,
            Instant fromTime,
            Instant toTime,
            int graphStyle
    ) {}

    public record UpdateLayoutRequest(
            UUID widgetId,
            UUID statusPageId,
            Integer posX,
            Integer posY,
            Instant fromTime,
            Instant toTime,
            Integer graphStyle
    ) {}

    public record LayoutResponse(
            UUID id,
            UUID widgetId,
            UUID statusPageId,
            int posX,
            int posY,
            Instant fromTime,
            Instant toTime,
            int graphStyle
    ) {}
}