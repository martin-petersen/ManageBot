package br.com.managebot.repository;

import br.com.managebot.model.Location;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {
    Optional<Location> findByLocation(String location);
    List<Location> findByDescription(String description);
}
