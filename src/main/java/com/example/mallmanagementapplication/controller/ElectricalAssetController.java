package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.ElectricalType;
import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.service.ElectricalAssetService;
import com.example.mallmanagementapplication.service.FloorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assets")
public class ElectricalAssetController {

    private final ElectricalAssetService service;
    private final FloorService floorService;

    public ElectricalAssetController(ElectricalAssetService service, FloorService floorService) {
        this.service = service;
        this.floorService = floorService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("assets", service.getAll());
        return "assets/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("asset", service.getById(id));
        return "assets/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("asset", new ElectricalAsset());
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/form";
    }

    @PostMapping
    public String create(@ModelAttribute ElectricalAsset asset) {
        service.save(asset);
        return "redirect:/assets";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("asset", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ElectricalAsset updated) {
        ElectricalAsset existing = service.getById(id);

        existing.setFloor(updated.getFloor());
        existing.setType(updated.getType());
        existing.setStatus(updated.getStatus());

        service.save(existing);
        return "redirect:/assets";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/assets";
    }
}
