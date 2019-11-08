package br.com.managebot.repository;

import br.com.managebot.model.Category;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategory(String category);
    List<Category> findByDescription(String description);
}
