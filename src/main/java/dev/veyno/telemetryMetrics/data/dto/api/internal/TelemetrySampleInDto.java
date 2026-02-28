package dev.veyno.telemetryMetrics.data.dto.api.internal;

import java.time.Instant;

public record TelemetrySampleInDto (Instant timestamp, double value) {}