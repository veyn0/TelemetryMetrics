package dev.veyno.telemetryMetrics.api.admin;


import dev.veyno.telemetryMetrics.data.dto.api.admin.CategoryDtos;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoriesController {

    private final TelemetryAdminService admin;

    public AdminCategoriesController(TelemetryAdminService admin) {
        this.admin = admin;
    }

    @GetMapping
    public List<CategoryDtos.CategoryResponse> list() {
        return admin.listCategories().stream()
                .map(c -> new CategoryDtos.CategoryResponse(c.getId(), c.getName()))
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryDtos.CategoryResponse get(@PathVariable UUID id) {
        var c = admin.getCategory(id);
        return new CategoryDtos.CategoryResponse(c.getId(), c.getName());
    }

    @PostMapping
    public CategoryDtos.CategoryResponse create(@RequestBody CategoryDtos.CreateCategoryRequest req) {
        var c = admin.createCategory(req.name());
        return new CategoryDtos.CategoryResponse(c.getId(), c.getName());
    }

    @PatchMapping("/{id}")
    public CategoryDtos.CategoryResponse update(@PathVariable UUID id, @RequestBody CategoryDtos.UpdateCategoryRequest req) {
        var c = admin.updateCategory(id, req.name());
        return new CategoryDtos.CategoryResponse(c.getId(), c.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        admin.deleteCategory(id);
    }
}