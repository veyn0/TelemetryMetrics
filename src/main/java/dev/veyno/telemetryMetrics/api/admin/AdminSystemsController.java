package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.dto.api.admin.SystemDtos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/systems")
public class AdminSystemsController {

    private final TelemetryAdminService admin;

    public AdminSystemsController(TelemetryAdminService admin) {
        this.admin = admin;
    }

    @GetMapping
    public List<SystemDtos.SystemResponse> list() {
        return admin.listSystems().stream()
                .map(s -> new SystemDtos.SystemResponse(s.getId(), s.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public SystemDtos.SystemResponse get(@PathVariable UUID id) {
        var s = admin.getSystem(id);
        return new SystemDtos.SystemResponse(s.getId(), s.getName());
    }

    @PostMapping
    public SystemDtos.SystemResponse create(@RequestBody SystemDtos.CreateSystemRequest req) {
        var s = admin.createSystem(req.name());
        return new SystemDtos.SystemResponse(s.getId(), s.getName());
    }

    @PatchMapping("/{id}")
    public SystemDtos.SystemResponse update(@PathVariable UUID id, @RequestBody SystemDtos.UpdateSystemRequest req) {
        var s = admin.updateSystem(id, req.name());
        return new SystemDtos.SystemResponse(s.getId(), s.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        admin.deleteSystem(id);
    }
}