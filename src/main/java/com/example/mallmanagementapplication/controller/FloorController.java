package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.MallService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/floors")
public class FloorController {

    private final FloorService floorService;
    private final MallService mallService;

    public FloorController(FloorService floorService, MallService mallService) {
        this.floorService = floorService;
        this.mallService = mallService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("floors", floorService.getAll());
        return "floors/index";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("floor", new Floor());
        model.addAttribute("malls", mallService.getAll());
        return "floors/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("floor") Floor floor,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("malls", mallService.getAll());
            return "floors/new";
        }

        try {
            floorService.save(floor);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("malls", mallService.getAll());
            return "floors/new";
        }

        return "redirect:/floors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("floor", floorService.getById(id));
        model.addAttribute("malls", mallService.getAll());
        return "floors/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("floor") Floor updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("malls", mallService.getAll());
            return "floors/edit";
        }

        Floor existing = floorService.getById(id);
        existing.setLevel(updated.getLevel());
        existing.setMall(updated.getMall());

        try {
            floorService.save(existing);
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("malls", mallService.getAll());
            return "floors/edit";
        }

        return "redirect:/floors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        floorService.delete(id);
        return "redirect:/floors";
    }

    /**
     * 🔥 DETAILS COMPLET
     */
    @Transactional
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {

        Floor floor = floorService.getById(id);

        model.addAttribute("floor", floor);
        model.addAttribute("shops", floor.getShops());
        model.addAttribute("assets", floor.getElectricalAssets());
        model.addAttribute("assignments", floor.getAssignments());
        model.addAttribute("tasks", floorService.getTasksForFloor(floor));

        return "floors/details";
    }
}
