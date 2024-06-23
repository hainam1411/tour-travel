package org.example.tourtravel.service;

import org.example.tourtravel.model.DTO.TypeDTO;
import org.example.tourtravel.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITypeService extends  IService<Type>{
    Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable);
}
