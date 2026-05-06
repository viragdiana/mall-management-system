package com.example.mallmanagementapplication.controller;

import com.example.mallmanagementapplication.model.Shop;
import com.example.mallmanagementapplication.model.ShopType;
import com.example.mallmanagementapplication.service.FloorService;
import com.example.mallmanagementapplication.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService service;
    private final FloorService floorService;

    public ShopController(ShopService service, FloorService floorService) {
        this.service = service;
        this.floorService = floorService;
    }

    /* ===================== LIST + FILTER + SORT ===================== */
    @GetMapping
    public String index(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {
        // allow only safe sort fields
        if (!sortBy.equals("name") && !sortBy.equals("type")) {
            sortBy = "name";
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        model.addAttribute(
                "shops",
                service.getFilteredAndSortedByName(name, sort)
        );

        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);

        return "shops/index";
    }

    /* ===================== DETAILS ===================== */
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("shop", service.getById(id));
        return "shops/details";
    }

    /* ===================== NEW FORM ===================== */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("shop", new Shop());

        // dropdown Floor
        model.addAttribute("floors", floorService.getAll());

        // dropdown Type (ENUM)
        model.addAttribute("types", ShopType.values());

        return "shops/new";
    }

    /* ===================== CREATE ===================== */
    @PostMapping
    public String create(
            @Valid @ModelAttribute("shop") Shop shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // re-populate dropdowns on error
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/new";
        }

        service.save(shop);
        return "redirect:/shops";
    }

    /* ===================== EDIT FORM ===================== */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("shop", service.getById(id));
        model.addAttribute("floors", floorService.getAll());
        model.addAttribute("types", ShopType.values());
        return "shops/edit";
    }

    /* ===================== UPDATE ===================== */
    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("shop") Shop updated,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("floors", floorService.getAll());
            model.addAttribute("types", ShopType.values());
            return "shops/edit";
        }

        Shop existing = service.getById(id);
        existing.setName(updated.getName());
        existing.setOwnerName(updated.getOwnerName());
        existing.setType(updated.getType());
        existing.setAreaSqm(updated.getAreaSqm());
        existing.setFloor(updated.getFloor());

        service.save(existing);
        return "redirect:/shops";
    }

    /* ===================== DELETE ===================== */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/shops";
    }
}