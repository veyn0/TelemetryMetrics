package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.dto.api.GraphDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPageGraph;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageGraphRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/graphs")
public class AdminGraphsController {

    private final TelemetryStatusPageGraphRepository repo;

    public AdminGraphsController(TelemetryStatusPageGraphRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<GraphDtos.GraphResponse> list(@RequestParam(required = false) UUID datasourceId) {
        List<TelemetryStatusPageGraph> graphs = (datasourceId == null)
                ? repo.findAll()
                : repo.findByDatasourceId(datasourceId);

        return graphs.stream()
                .map(g -> new GraphDtos.GraphResponse(g.getId(), g.getDatasourceId(), g.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public GraphDtos.GraphResponse get(@PathVariable UUID id) {
        TelemetryStatusPageGraph g = repo.findById(id).orElseThrow();
        return new GraphDtos.GraphResponse(g.getId(), g.getDatasourceId(), g.getName());
    }

    @PostMapping
    public GraphDtos.GraphResponse create(@RequestBody GraphDtos.CreateGraphRequest req) {
        TelemetryStatusPageGraph g = repo.save(new TelemetryStatusPageGraph(req.datasourceId(), req.name()));
        return new GraphDtos.GraphResponse(g.getId(), g.getDatasourceId(), g.getName());
    }

    @PatchMapping("/{id}")
    public GraphDtos.GraphResponse update(@PathVariable UUID id, @RequestBody GraphDtos.UpdateGraphRequest req) {
        TelemetryStatusPageGraph g = repo.findById(id).orElseThrow();
        if (req.datasourceId() != null) g.setDatasourceId(req.datasourceId());
        if (req.name() != null) g.setName(req.name());
        return new GraphDtos.GraphResponse(g.getId(), g.getDatasourceId(), g.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}