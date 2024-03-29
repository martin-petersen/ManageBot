package br.com.managebot.endpoint;


import br.com.managebot.error.ResourceNorFoundException;
import br.com.managebot.model.Category;
import br.com.managebot.model.Item;
import br.com.managebot.model.Location;
import br.com.managebot.repository.CategoryRepository;
import br.com.managebot.repository.ItemRepository;
import br.com.managebot.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
@RequestMapping("items")
public class ItemEndpoint {
    private final ItemRepository itemDAO;
    private final CategoryRepository categoryDAO;
    private final LocationRepository locationDAO;

    @Autowired
    public ItemEndpoint(ItemRepository itemDAO, CategoryRepository categoryDAO, LocationRepository locationDAO) {
        this.itemDAO = itemDAO;
        this.categoryDAO = categoryDAO;
        this.locationDAO = locationDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(itemDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getItemById(@PathVariable("id") Long id) {
        verifyID(id);
        Optional<Item> item = itemDAO.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping(path = "/byTombo/{tombo}")
    public ResponseEntity<?> findByTombo(@PathVariable("tombo") int tombo) {
        Optional<Item> item = Optional.ofNullable(itemDAO.findByTombo(tombo));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping(path = "/byItem/{Item}")
    public ResponseEntity<?> findByItem(@PathVariable String Item) {
        return new ResponseEntity<>(itemDAO.findByItem("%" + Item.toLowerCase() + "%"), HttpStatus.OK);
    }

    @GetMapping(path = "/byCategory/{Category}")
    public ResponseEntity<?> findByCategory(@PathVariable String Category) {
        Optional<br.com.managebot.model.Category> category = categoryDAO.findByCategory(Category);
        return new ResponseEntity<>(itemDAO.findByCategory(category.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/byLocation/{Location}")
    public ResponseEntity<?> findByLocation(@PathVariable String Location) {
        Optional<br.com.managebot.model.Location> location = locationDAO.findByLocation(Location);
        return new ResponseEntity<>(itemDAO.findByLocation(location.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/byDescription/{Description}")
    public ResponseEntity<?> findByDescription(@PathVariable String Description) {
        return new ResponseEntity<>(itemDAO.findByDescription(Description), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Item item) {
        Category category = categoryDAO.findByCategory(item.getCategory().getCategory()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Location location = locationDAO.findByLocation(item.getLocation().getLocation()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        item.setCategory(category);
        item.setLocation(location);

        return new ResponseEntity<>(itemDAO.save(item),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try{
            verifyID(id);
            Optional<Item> item = itemDAO.findById(id);
            item.get().setCategory(null);
            item.get().setLocation(null);
            update(item.get());
            itemDAO.deleteById(id);
        } catch (ResourceNorFoundException e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Item item) {
        verifyID(item.getId());
        itemDAO.save(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyID(Long id) {
        if(!itemDAO.findById(id).isPresent()) {
            throw new ResourceNorFoundException("Item not found for ID: " + id);
        }
    }
}
