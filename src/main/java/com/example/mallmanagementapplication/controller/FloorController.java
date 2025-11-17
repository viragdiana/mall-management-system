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

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("floors", service.getAllFloors());
        return "floors/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("floor", service.getFloor(id));
        return "floors/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("floor", new Floor());
        return "floors/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute Floor floor) {
        service.addFloor(floor);
        return "redirect:/floors";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("floor", service.getFloor(id));
        return "floors/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Floor updated) {
        Floor existing = service.getFloor(id);
        existing.setNumber(updated.getNumber());
        service.addFloor(existing);
        return "redirect:/floors";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteFloor(id);
        return "redirect:/floors";
    }
}
