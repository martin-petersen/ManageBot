package br.com.managebot.repository;

import br.com.managebot.model.Location;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {
    List<Location> findByLocation(String location);
    List<Location> findByDescription(String description);
}
