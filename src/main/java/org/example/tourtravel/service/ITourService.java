package org.example.tourtravel.service;

import org.example.tourtravel.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITourService extends IService<Tour>{
    Page<Tour> findByNameContainingPageAndSort(String name , Pageable pageable);
    Page<Tour> findAllPageAndSort(Pageable pageable);
}
