package com.trelloshopee.trelloshopee.controller;

import com.trelloshopee.trelloshopee.model.Item;
import com.trelloshopee.trelloshopee.model.Lista;
import com.trelloshopee.trelloshopee.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/lista")
public class ListaController {

    @Autowired
    private ListaRepository listaRepository;

    @GetMapping
    public ResponseEntity<List<Lista>> getAll(){
        return ResponseEntity.ok(listaRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lista> getById(@PathVariable long id){
        return listaRepository.findById(id)
                .map(resp-> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Lista> postLista(@Validated @RequestBody Lista lista){
        return ResponseEntity.status(HttpStatus.CREATED).body(listaRepository.save(lista));
    }

    @PutMapping
    public ResponseEntity<Lista> putLista(@Validated @RequestBody Lista lista) {

        return listaRepository.findById(lista.getId())
                .map(resposta -> {
                    return ResponseEntity.ok().body(listaRepository.save(lista));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLista(@PathVariable long id) {

        return listaRepository.findById(id)
                .map(resposta -> {
                    listaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
