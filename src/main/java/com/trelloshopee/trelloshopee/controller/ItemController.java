package com.trelloshopee.trelloshopee.controller;

import com.trelloshopee.trelloshopee.model.Item;
import com.trelloshopee.trelloshopee.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> getAll(){
        return ResponseEntity.ok(itemRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable long id){
        return itemRepository.findById(id)
                .map(resp-> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Item> postItem(@Validated @RequestBody Item item){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
    }

    @PutMapping
    public ResponseEntity<Item> putItem(@Validated @RequestBody Item item) {

        return itemRepository.findById(item.getId())
                .map(resposta -> {
                    return ResponseEntity.ok().body(itemRepository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable long id) {

        return itemRepository.findById(id)
                .map(resposta -> {
                    itemRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
