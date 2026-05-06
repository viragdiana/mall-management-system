package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Mall;
import com.example.mallmanagementapplication.service.MallService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
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

    /* ========== LIST + FILTER + SORT ========== */
    @GetMapping
    public String index(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        // allow only safe sort fields
        if (!sortBy.equals("name") && !sortBy.equals("city")) {
            sortBy = "name";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        model.addAttribute(
                "malls",
                service.getFilteredAndSortedByName(name, sort)
        );

        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "malls/index";
    }

    /* ========== DETAILS ========== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("mall", service.getById(id));
        return "malls/details";
    }

    /* ========== NEW ========== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("mall", new Mall());
        return "malls/new";
    }

    /* ========== CREATE ========== */
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

    /* ========== EDIT ========== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("mall", service.getById(id));
        return "malls/edit";
    }

    /* ========== UPDATE ========== */
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

    /* ========== DELETE ========== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/malls";
    }
}