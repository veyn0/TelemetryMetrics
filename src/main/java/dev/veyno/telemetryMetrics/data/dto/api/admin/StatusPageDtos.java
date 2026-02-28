package dev.veyno.telemetryMetrics.data.dto.api.admin;


import java.util.UUID;

public final class StatusPageDtos {

    public record CreateStatusPageRequest(String name) {}
    public record UpdateStatusPageRequest(String name) {}

    public record StatusPageResponse(UUID id, String name) {}
}