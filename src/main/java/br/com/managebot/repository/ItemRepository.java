package br.com.managebot.repository;

import br.com.managebot.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByItem(String name);
    List<Item> findByDescription(String description);
    List<Item> findByLocation(String location);
    List<Item> findByCategory(String category);
    @Query(value = "SELECT DISTINCT LOCATION FROM ITEM", nativeQuery = true)
    List<String> listLocations();
    @Query(value = "SELECT DISTINCT CATEGORY FROM ITEM", nativeQuery = true)
    List<String> listCategories();
}
