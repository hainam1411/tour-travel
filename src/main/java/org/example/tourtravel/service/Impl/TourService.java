package org.example.tourtravel.service.Impl;

import org.example.tourtravel.model.Tour;
import org.example.tourtravel.repository.ITourRepository;
import org.example.tourtravel.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TourService implements ITourService {
    private ITourRepository tourRepository;

    @Autowired
    public TourService(ITourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }


    @Override
    public void save(Tour tour) {
        if (tourRepository.existsByCode(tour.getCode())) {
            throw new IllegalArgumentException("Ma tour nay da ton tai");
        }
        tourRepository.save(tour);
    }

    @Override
    public void delete(Long id) {
        tourRepository.deleteById(id);
    }

    @Override
    public Iterable findAll() {
       return tourRepository.findAll();
    }


    @Override
    public Optional findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Page<Tour> findByNameContainingPageAndSort(String name, Pageable pageable) {
        return tourRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Tour> findAllPageAndSort(Pageable pageable) {
        return tourRepository.findAll(pageable);
    }

}
