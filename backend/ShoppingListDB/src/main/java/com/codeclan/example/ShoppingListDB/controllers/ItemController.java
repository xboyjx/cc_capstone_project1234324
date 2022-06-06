package com.codeclan.example.ShoppingListDB.controllers;

import com.codeclan.example.ShoppingListDB.models.Item;
import com.codeclan.example.ShoppingListDB.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable (value = "itemId") Long itemId){
        itemRepository.deleteById(itemId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getByItemId(@PathVariable (value="itemId") Long itemId){
        return new ResponseEntity<>(itemRepository.findById(itemId), HttpStatus.OK);
    }

    @GetMapping("/name=={itemName}")
    public ResponseEntity getByItemName(@PathVariable (value="itemName") String itemName){
        return new ResponseEntity<>(itemRepository.findByName(itemName), HttpStatus.OK);
    }

    @GetMapping("/name_search=={itemName}")
    public ResponseEntity<List<Item>> getItemsWithNameLike(@PathVariable (value = "itemName") String itemName){
        return new ResponseEntity<>(itemRepository.findByNameContaining(itemName), HttpStatus.OK);
    }
}
