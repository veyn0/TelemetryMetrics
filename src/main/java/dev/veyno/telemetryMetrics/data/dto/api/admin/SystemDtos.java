package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.util.UUID;

public final class SystemDtos {

    public record CreateSystemRequest(String name) {}
    public record UpdateSystemRequest(String name) {}

    public record SystemResponse(UUID id, String name) {}
}