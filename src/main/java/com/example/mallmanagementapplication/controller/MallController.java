package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.service.MallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/malls")
public class MallController {

    private final MallService service;

    public MallController(MallService service) {
        this.service = service;
    }

    // LIST
    @GetMapping
    public String index(Model model) {
        model.addAttribute("malls", service.getAllMalls());
        return "malls/index";
    }

    // DETAILS
    @GetMapping("/{id}")
    public String details(@PathVariable String id, Model model) {
        model.addAttribute("mall", service.getMall(id));
        return "malls/details";
    }

    // CREATE FORM
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("mall", new Mall());
        return "malls/form";
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute Mall mall) {
        service.addMall(mall);
        return "redirect:/malls";
    }

    // EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("mall", service.getMall(id));
        return "malls/edit";
    }

    // UPDATE
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute Mall updated) {
        Mall existing = service.getMall(id);

        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setCountry(updated.getCountry());

        service.addMall(existing);
        return "redirect:/malls";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteMall(id);
        return "redirect:/malls";
    }
}
