package org.example.tourtravel.controller;

import org.example.tourtravel.model.Type;
import org.example.tourtravel.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypeRestController {
    @Autowired
    private ITypeService typeService;

    @GetMapping
    public ResponseEntity<Iterable<Type>> showType(){
        List<Type> types = (List<Type>) typeService.findAll();
        if(types.isEmpty()){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(types, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
    public ResponseEntity deleteType(@PathVariable Long id){
        typeService.delete(id);
        return ResponseEntity.ok().build();
        }
}

