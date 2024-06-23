package org.example.tourtravel.service.Impl;

import org.example.tourtravel.model.DTO.TypeDTO;
import org.example.tourtravel.model.Type;
import org.example.tourtravel.repository.ITypeRepository;
import org.example.tourtravel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TypeService implements ITypeService {
    @Autowired
    private final ITypeRepository typeRepository;

    public TypeService(ITypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Page<TypeDTO> findQuantityInTypeByIdType(Pageable pageable) {
        return typeRepository.findQuantityInTypeByIdType(pageable);
    }
}
