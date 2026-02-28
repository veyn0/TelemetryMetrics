package dev.veyno.telemetryMetrics.api.admin;

import dev.veyno.telemetryMetrics.data.dto.api.admin.WidgetDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPageWidget;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageWidgetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/widgets")
public class AdminWidgetsController {

    private final TelemetryStatusPageWidgetRepository repo;

    public AdminWidgetsController(TelemetryStatusPageWidgetRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<WidgetDtos.WidgetResponse> list(@RequestParam(required = false) UUID graphId) {
        List<TelemetryStatusPageWidget> widgets = (graphId == null)
                ? repo.findAll()
                : repo.findByGraphId(graphId);

        return widgets.stream()
                .map(w -> new WidgetDtos.WidgetResponse(w.getId(), w.getGraphId()))
                .toList();
    }

    @GetMapping("/{id}")
    public WidgetDtos.WidgetResponse get(@PathVariable UUID id) {
        TelemetryStatusPageWidget w = repo.findById(id).orElseThrow();
        return new WidgetDtos.WidgetResponse(w.getId(), w.getGraphId());
    }

    @PostMapping
    public WidgetDtos.WidgetResponse create(@RequestBody WidgetDtos.CreateWidgetRequest req) {
        TelemetryStatusPageWidget w = repo.save(new TelemetryStatusPageWidget(req.graphId()));
        return new WidgetDtos.WidgetResponse(w.getId(), w.getGraphId());
    }

    @PatchMapping("/{id}")
    public WidgetDtos.WidgetResponse update(@PathVariable UUID id, @RequestBody WidgetDtos.UpdateWidgetRequest req) {
        TelemetryStatusPageWidget w = repo.findById(id).orElseThrow();
        if (req.graphId() != null) w.setGraphId(req.graphId());
        return new WidgetDtos.WidgetResponse(w.getId(), w.getGraphId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}