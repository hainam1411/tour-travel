package org.example.tourtravel.repository;

import org.example.tourtravel.model.DTO.TypeDTO;
import org.example.tourtravel.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeRepository extends JpaRepository<Type, Long> {
    @Query(value = "select type.name as name , count(tour.type_id) as quantity from type left join tour on type.id = tour.type_id group by type.id",countQuery = "SELECT count(*) FROM type",nativeQuery = true)
    Page<TypeDTO> findQuantityInTypeByIdType (Pageable pageable);
}
