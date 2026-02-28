package dev.veyno.telemetryMetrics.api.admin;

import dev.veyno.telemetryMetrics.data.dto.api.admin.DatasourceDtos;
import dev.veyno.telemetryMetrics.data.entity.statuspage.TelemetryStatusPageDatasource;
import dev.veyno.telemetryMetrics.data.repository.statuspage.TelemetryStatusPageDatasourceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/datasources")
public class AdminDatasourcesController {

    private final TelemetryStatusPageDatasourceRepository repo;

    public AdminDatasourcesController(TelemetryStatusPageDatasourceRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<DatasourceDtos.DatasourceResponse> list() {
        return repo.findAll().stream()
                .map(d -> new DatasourceDtos.DatasourceResponse(
                        d.getId(),
                        DatasourceDtos.DatasourceType.valueOf(d.getType().name()),
                        d.getServiceId(),
                        d.getCompositionType() == null ? null : DatasourceDtos.CompositionType.valueOf(d.getCompositionType().name())
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public DatasourceDtos.DatasourceResponse get(@PathVariable UUID id) {
        TelemetryStatusPageDatasource d = repo.findById(id).orElseThrow();
        return new DatasourceDtos.DatasourceResponse(
                d.getId(),
                DatasourceDtos.DatasourceType.valueOf(d.getType().name()),
                d.getServiceId(),
                d.getCompositionType() == null ? null : DatasourceDtos.CompositionType.valueOf(d.getCompositionType().name())
        );
    }

    @PostMapping
    public DatasourceDtos.DatasourceResponse create(@RequestBody DatasourceDtos.CreateDatasourceRequest req) {
        TelemetryStatusPageDatasource d = repo.save(new TelemetryStatusPageDatasource(
                TelemetryStatusPageDatasource.Type.valueOf(req.type().name()),
                req.serviceId(),
                req.compositionType() == null ? null : TelemetryStatusPageDatasource.CompositionType.valueOf(req.compositionType().name())
        ));
        return new DatasourceDtos.DatasourceResponse(
                d.getId(),
                DatasourceDtos.DatasourceType.valueOf(d.getType().name()),
                d.getServiceId(),
                d.getCompositionType() == null ? null : DatasourceDtos.CompositionType.valueOf(d.getCompositionType().name())
        );
    }

    @PatchMapping("/{id}")
    public DatasourceDtos.DatasourceResponse update(@PathVariable UUID id, @RequestBody DatasourceDtos.UpdateDatasourceRequest req) {
        TelemetryStatusPageDatasource d = repo.findById(id).orElseThrow();

        if (req.type() != null) d.setType(TelemetryStatusPageDatasource.Type.valueOf(req.type().name()));
        if (req.serviceId() != null) d.setServiceId(req.serviceId());
        if (req.compositionType() != null) {
            d.setCompositionType(TelemetryStatusPageDatasource.CompositionType.valueOf(req.compositionType().name()));
        }

        return new DatasourceDtos.DatasourceResponse(
                d.getId(),
                DatasourceDtos.DatasourceType.valueOf(d.getType().name()),
                d.getServiceId(),
                d.getCompositionType() == null ? null : DatasourceDtos.CompositionType.valueOf(d.getCompositionType().name())
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repo.deleteById(id);
    }
}