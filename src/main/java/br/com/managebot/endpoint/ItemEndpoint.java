package br.com.managebot.endpoint;


import br.com.managebot.error.CustomErroType;
import br.com.managebot.model.Item;
import br.com.managebot.repository.ItemRepository;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("items")
public class ItemEndpoint {
    private final ItemRepository itemDAO;

    @Autowired
    public ItemEndpoint(ItemRepository itemDAO) {
        this.itemDAO = itemDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(itemDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable("id") Long id) {
        Optional<Item> item = itemDAO.findById(id);
        if(!item.isPresent()) {
            return new ResponseEntity<>(new CustomErroType("Item not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Item item) {
        return new ResponseEntity<>(itemDAO.save(item),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        itemDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Item item) {
        itemDAO.save(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
