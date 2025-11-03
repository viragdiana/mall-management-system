package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.ElectricalType;
import com.example.mallmanagementapplication.service.ElectricalAssetService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
@RequestMapping("/assets")
public class ElectricalAssetController {

    private final ElectricalAssetService service;

    public ElectricalAssetController(ElectricalAssetService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("assets", service.getAllAssets());
        return "assets/index";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("asset", new ElectricalAsset());
        model.addAttribute("statuses", AssetStatus.values());
        model.addAttribute("types", ElectricalType.values());
        return "assets/form";
    }

    @PostMapping
    public String create(@ModelAttribute ElectricalAsset asset) {
        service.addAsset(asset);
        return "redirect:/assets";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteAsset(id);
        return "redirect:/assets";
    }
}