package br.com.managebot.repository;

import br.com.managebot.model.Category;
import br.com.managebot.model.Item;
import br.com.managebot.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("from Item i where lower(i.item) like :name")
    List<Item> findByItem(@Param("name") String nome);
    List<Item> findByDescription(String description);
    Item findByTombo(int tombo);
    List<Item> findByLocation(Location location);
    List<Item> findByCategory(Category category);
}
