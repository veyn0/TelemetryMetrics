package dev.veyno.telemetryMetrics.api.admin;

import dev.veyno.telemetryMetrics.data.dto.api.admin.TooltipDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPageWidgetTooltip;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageWidgetTooltipRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/widget-tooltips")
public class AdminTooltipsController {

    private final TelemetryStatusPageWidgetTooltipRepository repo;

    public AdminTooltipsController(TelemetryStatusPageWidgetTooltipRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<TooltipDtos.TooltipResponse> list(
            @RequestParam(required = false) UUID widgetId,
            @RequestParam(required = false) UUID graphId
    ) {
        List<TelemetryStatusPageWidgetTooltip> rows;
        if (widgetId != null) rows = repo.findByWidgetId(widgetId);
        else if (graphId != null) rows = repo.findByGraphId(graphId);
        else rows = repo.findAll();

        return rows.stream()
                .map(t -> new TooltipDtos.TooltipResponse(t.getId(), t.getWidgetId(), t.getGraphId()))
                .toList();
    }

    @GetMapping("/{id}")
    public TooltipDtos.TooltipResponse get(@PathVariable UUID id) {
        TelemetryStatusPageWidgetTooltip t = repo.findById(id).orElseThrow();
        return new TooltipDtos.TooltipResponse(t.getId(), t.getWidgetId(), t.getGraphId());
    }

    @PostMapping
    public TooltipDtos.TooltipResponse create(@RequestBody TooltipDtos.CreateTooltipRequest req) {
        TelemetryStatusPageWidgetTooltip t = repo.save(new TelemetryStatusPageWidgetTooltip(req.widgetId(), req.graphId()));
        return new TooltipDtos.TooltipResponse(t.getId(), t.getWidgetId(), t.getGraphId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}