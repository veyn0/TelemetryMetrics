package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.dto.api.admin.ServiceDtos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/services")
public class AdminServicesController {

    private final TelemetryAdminService admin;

    public AdminServicesController(TelemetryAdminService admin) {
        this.admin = admin;
    }

    @GetMapping
    public List<ServiceDtos.ServiceResponse> list(
            @RequestParam(required = false) UUID systemId,
            @RequestParam(required = false) UUID categoryId
    ) {
        return admin.listServices(systemId, categoryId).stream()
                .map(s -> new ServiceDtos.ServiceResponse(
                        s.getId(),
                        s.getSystemId(),
                        s.getCategoryId(),
                        s.getName(),
                        s.getCreatedAt()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ServiceDtos.ServiceResponse get(@PathVariable UUID id) {
        var s = admin.getService(id);
        return new ServiceDtos.ServiceResponse(s.getId(), s.getSystemId(), s.getCategoryId(), s.getName(), s.getCreatedAt());
    }

    @PostMapping
    public ServiceDtos.ServiceResponse create(@RequestBody ServiceDtos.CreateServiceRequest req) {
        var s = admin.createService(req.systemId(), req.categoryId(), req.name());
        return new ServiceDtos.ServiceResponse(s.getId(), s.getSystemId(), s.getCategoryId(), s.getName(), s.getCreatedAt());
    }

    @PatchMapping("/{id}")
    public ServiceDtos.ServiceResponse update(@PathVariable UUID id, @RequestBody ServiceDtos.UpdateServiceRequest req) {
        var s = admin.updateService(id, req.systemId(), req.categoryId(), req.name());
        return new ServiceDtos.ServiceResponse(s.getId(), s.getSystemId(), s.getCategoryId(), s.getName(), s.getCreatedAt());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        admin.deleteService(id);
    }
}