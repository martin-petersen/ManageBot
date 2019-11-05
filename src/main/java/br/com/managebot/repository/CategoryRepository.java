package br.com.managebot.repository;

import br.com.managebot.model.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByCategory(String category);
    List<Category> findByDescription(String description);
}
