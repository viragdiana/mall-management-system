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

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("assets", service.getAllAssets());
        return "assets/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("asset", service.getAsset(id));
        return "assets/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("asset", new ElectricalAsset());
        model.addAttribute("floors", floorService.getAllFloors());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute ElectricalAsset asset) {
        service.addAsset(asset);
        return "redirect:/assets";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("asset", service.getAsset(id));
        model.addAttribute("floors", floorService.getAllFloors());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute ElectricalAsset updated) {
        ElectricalAsset existing = service.getAsset(id);

        existing.setFloorId(updated.getFloorId());
        existing.setType(updated.getType());
        existing.setStatus(updated.getStatus());

        service.addAsset(existing);
        return "redirect:/assets";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteAsset(id);
        return "redirect:/assets";
    }
}
