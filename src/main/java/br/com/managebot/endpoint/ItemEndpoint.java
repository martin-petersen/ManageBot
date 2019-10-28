package br.com.managebot.endpoint;


import br.com.managebot.error.CustomErroType;
import br.com.managebot.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;


@RestController
@RequestMapping("items")
public class ItemEndpoint {
    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(Item.itemList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable("id") int id) {
        Item item = new Item();
        item.setId(id);
        int index = Item.itemList.indexOf(item);

        if(index == -1) {
            return new ResponseEntity<>(new CustomErroType("Item not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Item.itemList.get(index), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Item item) {
        Item.itemList.add(item);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> remove(@RequestBody Item item) {
        Item.itemList.remove(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Item item) {
        Item.itemList.remove(item);
        Item.itemList.add(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
