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

    @GetMapping
    public String index(Model model) {
        model.addAttribute("floors", service.getAll());
        return "floors/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("floor", service.getById(id));
        return "floors/details";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("floor", new Floor());
        return "floors/form";
    }

    @PostMapping
    public String create(@ModelAttribute Floor floor) {
        service.save(floor);
        return "redirect:/floors";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("floor", service.getById(id));
        return "floors/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Floor updated) {
        Floor existing = service.getById(id);
        existing.setLevel(updated.getLevel());
        service.save(existing);
        return "redirect:/floors";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/floors";
    }
}
