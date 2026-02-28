package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.dto.api.admin.StatusPageDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPage;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/status-pages")
public class AdminStatusPagesController {

    private final TelemetryStatusPageRepository repo;

    public AdminStatusPagesController(TelemetryStatusPageRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<StatusPageDtos.StatusPageResponse> list() {
        return repo.findAll().stream()
                .map(p -> new StatusPageDtos.StatusPageResponse(p.getId(), p.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public StatusPageDtos.StatusPageResponse get(@PathVariable UUID id) {
        TelemetryStatusPage p = repo.findById(id).orElseThrow();
        return new StatusPageDtos.StatusPageResponse(p.getId(), p.getName());
    }

    @PostMapping
    public StatusPageDtos.StatusPageResponse create(@RequestBody StatusPageDtos.CreateStatusPageRequest req) {
        TelemetryStatusPage p = repo.save(new TelemetryStatusPage(req.name()));
        return new StatusPageDtos.StatusPageResponse(p.getId(), p.getName());
    }

    @PatchMapping("/{id}")
    public StatusPageDtos.StatusPageResponse update(@PathVariable UUID id, @RequestBody StatusPageDtos.UpdateStatusPageRequest req) {
        TelemetryStatusPage p = repo.findById(id).orElseThrow();
        if (req.name() != null) p.setName(req.name());
        return new StatusPageDtos.StatusPageResponse(p.getId(), p.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}