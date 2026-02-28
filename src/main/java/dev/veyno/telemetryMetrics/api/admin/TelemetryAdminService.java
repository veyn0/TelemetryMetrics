package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.entity.TelemetryService;
import dev.veyno.telemetryMetrics.data.entity.TelemetryServiceCategory;
import dev.veyno.telemetryMetrics.data.entity.TelemetrySystem;
import dev.veyno.telemetryMetrics.data.repository.TelemetryServiceCategoryRepository;
import dev.veyno.telemetryMetrics.data.repository.TelemetryServiceRepository;
import dev.veyno.telemetryMetrics.data.repository.TelemetrySystemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TelemetryAdminService {

    private final TelemetrySystemRepository systemRepo;
    private final TelemetryServiceCategoryRepository categoryRepo;
    private final TelemetryServiceRepository serviceRepo;

    public TelemetryAdminService(
            TelemetrySystemRepository systemRepo,
            TelemetryServiceCategoryRepository categoryRepo,
            TelemetryServiceRepository serviceRepo
    ) {
        this.systemRepo = systemRepo;
        this.categoryRepo = categoryRepo;
        this.serviceRepo = serviceRepo;
    }

    public List<TelemetrySystem> listSystems() {
        return systemRepo.findAll();
    }

    public TelemetrySystem getSystem(UUID id) {
        return systemRepo.findById(id).orElseThrow();
    }

    public TelemetrySystem createSystem(String name) {
        return systemRepo.save(new TelemetrySystem(name));
    }

    public TelemetrySystem updateSystem(UUID id, String name) {
        TelemetrySystem s = getSystem(id);
        s.setName(name);
        return s;
    }

    public void deleteSystem(UUID id) {
        systemRepo.deleteById(id);
    }

    public List<TelemetryServiceCategory> listCategories() {
        return categoryRepo.findAll();
    }

    public TelemetryServiceCategory getCategory(UUID id) {
        return categoryRepo.findById(id).orElseThrow();
    }

    public TelemetryServiceCategory createCategory(String name) {
        return categoryRepo.save(new TelemetryServiceCategory(name));
    }

    public TelemetryServiceCategory updateCategory(UUID id, String name) {
        TelemetryServiceCategory c = getCategory(id);
        c.setName(name);
        return c;
    }

    public void deleteCategory(UUID id) {
        categoryRepo.deleteById(id);
    }

    public List<TelemetryService> listServices(UUID systemId, UUID categoryId) {
        if (systemId != null && categoryId != null) {
            return serviceRepo.findBySystemIdAndCategoryId(systemId, categoryId);
        }
        if (systemId != null) {
            return serviceRepo.findAllBySystemId(systemId);
        }
        if (categoryId != null) {
            return serviceRepo.findAllByCategoryId(categoryId);
        }
        return serviceRepo.findAll();
    }

    public TelemetryService getService(UUID id) {
        return serviceRepo.findById(id).orElseThrow();
    }

    public TelemetryService createService(UUID systemId, UUID categoryId, String name) {
        if (!systemRepo.existsById(systemId) || !categoryRepo.existsById(categoryId)) {
            throw new IllegalArgumentException("systemId or categoryId does not exist");
        }
        return serviceRepo.save(new TelemetryService(systemId, categoryId, name, Instant.now()));
    }

    public TelemetryService updateService(UUID id, UUID systemId, UUID categoryId, String name) {
        TelemetryService svc = getService(id);

        if (systemId != null && !systemRepo.existsById(systemId)) {
            throw new IllegalArgumentException("systemId does not exist");
        }
        if (categoryId != null && !categoryRepo.existsById(categoryId)) {
            throw new IllegalArgumentException("categoryId does not exist");
        }

        if (systemId != null) svc.setSystemId(systemId);
        if (categoryId != null) svc.setCategoryId(categoryId);
        if (name != null) svc.setName(name);

        return svc;
    }

    public void deleteService(UUID id) {
        serviceRepo.deleteById(id);
    }
}