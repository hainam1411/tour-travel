package org.example.tourtravel.repository;

import org.example.tourtravel.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITourRepository extends JpaRepository<Tour, Long> {
    boolean existsByCode(String code);
    Page<Tour> findAll(Pageable pageable);
    Page<Tour> findByNameContaining(String destination, Pageable pageable);
}
