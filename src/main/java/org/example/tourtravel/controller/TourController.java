package org.example.tourtravel.controller;

import org.example.tourtravel.model.Tour;
import org.example.tourtravel.service.ITourService;
import org.example.tourtravel.service.ITypeService;
import org.example.tourtravel.service.Impl.TourService;
import org.example.tourtravel.service.Impl.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final TourService tourService;
    private final TypeService typeService;
    @Autowired
    public TourController(TourService tourService, TypeService typeService) {
        this.tourService = tourService;
        this.typeService = typeService;
    }


    @GetMapping("")
    public ModelAndView showList(@PageableDefault Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("tour/index");
        modelAndView.addObject("tours", tourService.findAllPageAndSort(pageable));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("tour/delete");
        modelAndView.addObject("tour", tourService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        tourService.delete(id);
        return "redirect:/tours";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("tour/create");
        modelAndView.addObject("tour", new Tour());
        modelAndView.addObject("types",typeService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Tour tour, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("tour/create");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        try {
            tourService.save(tour);
        }catch (IllegalArgumentException e){
            bindingResult.rejectValue("code","Khong the them tour moi",e.getMessage());
            ModelAndView modelAndView = new ModelAndView("tour/create");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/tours");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Optional<Tour> optionalTour = tourService.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();
            ModelAndView modelAndView = new ModelAndView("tour/update");
            modelAndView.addObject("tour", tour);
            modelAndView.addObject("types", typeService.findAll());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error/404");
            modelAndView.addObject("message", "Tour không tồn tại");
            return modelAndView;
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Tour tour , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("tour/update");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        try {
            tourService.save(tour);
        }catch (IllegalArgumentException e){
            bindingResult.rejectValue("code","Khong the sua tour nay",e.getMessage());
            ModelAndView modelAndView = new ModelAndView("tour/update");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/tours");
    }

    @GetMapping("/search")
    public ModelAndView showSearch(@RequestParam String s , @PageableDefault(value = 5) Pageable pageable) {
        Page<Tour> tours;
        if (s.isEmpty()) {
            tours = tourService.findAllPageAndSort(pageable);
        } else {
            tours = tourService.findByNameContainingPageAndSort(s , pageable);
        }
        ModelAndView modelAndView = new ModelAndView("tour/search");
        modelAndView.addObject("tours", tours);
        modelAndView.addObject("s",s);
        return modelAndView;
    }
}
