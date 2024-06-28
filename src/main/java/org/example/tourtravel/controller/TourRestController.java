package org.example.tourtravel.controller;

import org.example.tourtravel.model.Tour;
import org.example.tourtravel.service.ITourService;
import org.example.tourtravel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin("*")
public class TourRestController {
    @Autowired
    private ITourService tourService;

    @Autowired
    private ITypeService typeService;

    @GetMapping("")
    public ResponseEntity<Iterable<Tour>> showTour(){
        List<Tour> tour = (List<Tour>) tourService.findAll();
        if (tour.isEmpty()) {
            return new  ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }


  //  @GetMapping("/{id}")
}
