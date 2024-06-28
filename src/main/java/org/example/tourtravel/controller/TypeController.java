package org.example.tourtravel.controller;

import org.example.tourtravel.model.Type;
import org.example.tourtravel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/types")

public class TypeController {
    private final ITypeService typeService;

    @Autowired
    public TypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("type/index");
        modelAndView.addObject("types", typeService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("type/delete");
        modelAndView.addObject("type", typeService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        typeService.delete(id);
        return "redirect:/types";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Type type , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("type/create");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/types");
        typeService.save(type);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Type type = typeService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("type/update");
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Type type , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("type/update");
            modelAndView.addObject("type", type);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/types");
        typeService.save(type);
        return modelAndView;
    }

    @GetMapping("/statistic")
    public ModelAndView showStatistic(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("type/statistic");
        modelAndView.addObject("statistics", typeService.findQuantityInTypeByIdType(pageable));
        return modelAndView;
    }
}
