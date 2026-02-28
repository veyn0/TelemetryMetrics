package dev.veyno.telemetryMetrics.data.dto.api.admin;

import java.util.UUID;

public final class CategoryDtos {

    public record CreateCategoryRequest(String name) {}
    public record UpdateCategoryRequest(String name) {}

    public record CategoryResponse(UUID id, String name) {}
}