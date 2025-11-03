package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Floor;
import com.example.mallmanagementapplication.service.FloorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/floors")
public class FloorController {

    private final FloorService service;

    public FloorController(FloorService service) {
        this.service = service;
    }

    // Afișează toate etajele
    @GetMapping
    public String index(Model model) {
        model.addAttribute("floors", service.getAllFloors());
        return "floors/index";
    }

    // Formular pentru un etaj nou
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("floor", new Floor());
        return "floors/form";
    }

    // Creează etajul nou
    @PostMapping
    public String create(@ModelAttribute Floor floor) {
        service.addFloor(floor);
        return "redirect:/floors";
    }

    // Șterge etajul
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteFloor(id);
        return "redirect:/floors";
    }
}
