package org.example.tourtravel.controller;

import org.example.tourtravel.model.Tour;
import org.example.tourtravel.service.ITourService;
import org.example.tourtravel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{id}")
    public ResponseEntity<Tour> findTourById(@PathVariable Long id){
        Optional<Tour> tourOptional = tourService.findById(id);
        if (tourOptional.isPresent()){
            return new ResponseEntity<>(tourOptional.get(), HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Tour> saveTour(@PathVariable Long id, @RequestBody Tour tour ){
        tourService.save(tour);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tour){
        Optional<Tour> tourOptional = tourService.findById(id);
        if (tourOptional.isPresent()){
            tour.setId(tourOptional.get().getId());
            tourService.save(tour);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tour> deleteTour(@PathVariable Long id){
        Optional<Tour> tourOptional = tourService.findById(id);
        if (tourOptional.isPresent()){
            tourService.delete(tourOptional.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
