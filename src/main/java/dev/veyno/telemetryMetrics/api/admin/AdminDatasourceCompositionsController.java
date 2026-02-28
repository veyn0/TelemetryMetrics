package dev.veyno.telemetryMetrics.api.admin;

import dev.veyno.telemetryMetrics.data.dto.api.admin.DatasourceCompositionDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPageDatasourceComposition;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageDatasourceCompositionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/datasource-compositions")
public class AdminDatasourceCompositionsController {

    private final TelemetryStatusPageDatasourceCompositionRepository repo;

    public AdminDatasourceCompositionsController(TelemetryStatusPageDatasourceCompositionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<DatasourceCompositionDtos.CompositionResponse> list(@RequestParam(required = false) UUID datasourceId) {
        List<TelemetryStatusPageDatasourceComposition> rows = (datasourceId == null)
                ? repo.findAll()
                : repo.findByDatasourceId(datasourceId);

        return rows.stream()
                .map(r -> new DatasourceCompositionDtos.CompositionResponse(r.getId(), r.getDatasourceId(), r.getServiceId()))
                .toList();
    }

    @GetMapping("/{id}")
    public DatasourceCompositionDtos.CompositionResponse get(@PathVariable UUID id) {
        TelemetryStatusPageDatasourceComposition r = repo.findById(id).orElseThrow();
        return new DatasourceCompositionDtos.CompositionResponse(r.getId(), r.getDatasourceId(), r.getServiceId());
    }

    @PostMapping
    public DatasourceCompositionDtos.CompositionResponse create(@RequestBody DatasourceCompositionDtos.CreateCompositionRequest req) {
        TelemetryStatusPageDatasourceComposition r = repo.save(new TelemetryStatusPageDatasourceComposition(req.datasourceId(), req.serviceId()));
        return new DatasourceCompositionDtos.CompositionResponse(r.getId(), r.getDatasourceId(), r.getServiceId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}