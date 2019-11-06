package br.com.managebot.endpoint;

import br.com.managebot.error.AlreadyExistentExcpetion;
import br.com.managebot.error.ResourceNorFoundException;
import br.com.managebot.model.Category;
import br.com.managebot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("category")
public class CategoryEndpoint {
    private final CategoryRepository categoryDAO;
    @Autowired
    public CategoryEndpoint(CategoryRepository categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(categoryDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        verifyID(id);
        Optional<Category> category = categoryDAO.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(path = "/byCategoryName/{Category}")
    public ResponseEntity<?> findByCategory(@PathVariable String Category) {
        return new ResponseEntity<>(categoryDAO.findByCategory(Category), HttpStatus.OK);
    }

    @GetMapping(path = "/byCategoryDescription/{Description}")
    public ResponseEntity<?> findByDescription(@PathVariable String Description) {
        return new ResponseEntity<>(categoryDAO.findByDescription(Description), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryDAO.save(category),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        verifyID(id);
        categoryDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Category category) {
        verifyID(category.getId());
        categoryDAO.save(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyCategory(String category) {
        if(!categoryDAO.findByCategory(category).isEmpty()) {
            throw new AlreadyExistentExcpetion("Category already reported to: " + category);
        }
    }

    private void verifyID(Long id) {
        if(!categoryDAO.findById(id).isPresent()) {
            throw new ResourceNorFoundException("Category not found for ID: " + id);
        }
    }
}
