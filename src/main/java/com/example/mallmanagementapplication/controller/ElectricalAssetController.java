package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.AssetStatus;
import com.example.mallmanagementapplication.model.ElectricalAsset;
import com.example.mallmanagementapplication.model.ElectricalType;
import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.service.ElectricalAssetService;
import com.example.mallmanagementapplication.service.FloorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assets")
public class ElectricalAssetController {

    private final ElectricalAssetService service;
    private final FloorService floorService;

    public ElectricalAssetController(
            ElectricalAssetService service,
            FloorService floorService
    ) {
        this.service = service;
        this.floorService = floorService;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) AssetStatus status,
            @RequestParam(defaultValue = "type") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        if (!sortBy.equals("type") && !sortBy.equals("status")) {
            sortBy = "type";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        model.addAttribute(
                "assets",
                service.getFilteredAndSorted(status, sort)
        );

        model.addAttribute("statuses", AssetStatus.values());
        model.addAttribute("status", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "assets/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("asset", service.getById(id));
        return "assets/details";
    }

    /* ===================== NEW ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("asset", new ElectricalAsset());
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/new";
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("asset") ElectricalAsset asset,
            BindingResult bindingResult,
            @RequestParam("floor") Long floorId,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ElectricalType.values());
            model.addAttribute("statuses", AssetStatus.values());
            return "assets/new";
        }

        Floor floor = floorService.getById(floorId);
        asset.setFloor(floor);

        service.save(asset);
        return "redirect:/assets";
    }

    /* ===================== EDIT ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("asset", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ElectricalType.values());
        model.addAttribute("statuses", AssetStatus.values());
        return "assets/edit";
    }

    /* ===================== UPDATE ===================== */
    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("asset") ElectricalAsset updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ElectricalType.values());
            model.addAttribute("statuses", AssetStatus.values());
            return "assets/edit";
        }

        ElectricalAsset existing = service.getById(id);
        existing.setFloor(updated.getFloor());
        existing.setType(updated.getType());
        existing.setStatus(updated.getStatus());

        service.save(existing);
        return "redirect:/assets";
    }

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/assets";
    }
}