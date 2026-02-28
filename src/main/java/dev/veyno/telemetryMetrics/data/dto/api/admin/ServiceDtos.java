package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.time.Instant;
import java.util.UUID;

public final class ServiceDtos {

    public record CreateServiceRequest(UUID systemId, UUID categoryId, String name) {}
    public record UpdateServiceRequest(UUID systemId, UUID categoryId, String name) {}

    public record ServiceResponse(
            UUID id,
            UUID systemId,
            UUID categoryId,
            String name,
            Instant createdAt
    ) {}
}