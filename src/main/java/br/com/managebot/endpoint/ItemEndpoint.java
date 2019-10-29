package br.com.managebot.endpoint;


import br.com.managebot.error.CustomErroType;
import br.com.managebot.model.Item;
import br.com.managebot.repository.ItemRepository;
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

    @GetMapping(path = "/byItem/{Item}")
    public ResponseEntity<?> findByItem(@PathVariable String Item) {
        return new ResponseEntity<>(itemDAO.findByItem(Item), HttpStatus.OK);
    }

    @GetMapping(path = "/byCategory/{Category}")
    public ResponseEntity<?> findByCategory(@PathVariable String Category) {
        return new ResponseEntity<>(itemDAO.findByCategory(Category), HttpStatus.OK);
    }

    @GetMapping(path = "/byLocation/{Location}")
    public ResponseEntity<?> findByLocation(@PathVariable String Location) {
        return new ResponseEntity<>(itemDAO.findByLocation(Location), HttpStatus.OK);
    }

    @GetMapping(path = "/byDescription/{Description}")
    public ResponseEntity<?> findByDescription(@PathVariable String Description) {
        return new ResponseEntity<>(itemDAO.findByDescription(Description), HttpStatus.OK);
    }

    @GetMapping(path = "/locations")
    public ResponseEntity<?> listLocations() {
        return new ResponseEntity<>(itemDAO.listLocations(), HttpStatus.OK);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<?> listCategories() {
        return new ResponseEntity<>(itemDAO.listCategories(), HttpStatus.OK);
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
