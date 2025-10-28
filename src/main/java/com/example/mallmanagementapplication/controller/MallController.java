package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.service.MallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/malls")
public class MallController {

    private final MallService mallService;

    public MallController(MallService mallService) {
        this.mallService = mallService;
    }

    @PostMapping
    public void addMall(@RequestBody Mall mall) {
        mallService.addMall(mall);
    }

    @GetMapping
    public List<Mall> getAllMalls() {
        return mallService.getAllMalls();
    }

    @GetMapping("/{id}")
    public Optional<Mall> getMallById(@PathVariable String id) {
        return mallService.getMallById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMall(@PathVariable String id) {
        mallService.deleteMall(id);
    }
}
