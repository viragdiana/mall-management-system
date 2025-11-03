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

    // ✅ Afișează toate mall-urile
    @GetMapping
    public String index(Model model) {
        model.addAttribute("malls", service.getAllMalls());
        return "malls/index";
    }

    // ✅ Formular pentru mall nou
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("mall", new Mall());
        return "malls/form";
    }

    // ✅ Creează mall-ul nou
    @PostMapping
    public String create(@ModelAttribute Mall mall) {
        service.addMall(mall);
        return "redirect:/malls";
    }

    // ✅ Șterge mall-ul
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        service.deleteMall(id);
        return "redirect:/malls";
    }
}
