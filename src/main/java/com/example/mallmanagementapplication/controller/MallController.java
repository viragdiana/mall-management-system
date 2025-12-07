package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.service.MallService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/malls")
public class MallController {

    private final MallService service;

    public MallController(MallService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("malls", service.getAll());
        return "malls/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("mall", service.getById(id));
        return "malls/details";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("mall", new Mall());
        return "malls/new";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("mall") Mall mall,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "malls/new";
        }

        service.save(mall);
        return "redirect:/malls";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("mall", service.getById(id));
        return "malls/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("mall") Mall updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "malls/edit";
        }

        Mall existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setCity(updated.getCity());
        existing.setCountry(updated.getCountry());

        service.save(existing);
        return "redirect:/malls";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/malls";
    }
}
