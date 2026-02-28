package dev.veyno.telemetryMetrics.data.dto.api.internal;

import java.util.List;
import java.util.UUID;

public record IngestTelemetrySamplesRequest (UUID serviceId, List<TelemetrySampleInDto> samples) {}