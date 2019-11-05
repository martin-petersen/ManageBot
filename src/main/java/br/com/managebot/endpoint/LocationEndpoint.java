package br.com.managebot.endpoint;


import br.com.managebot.error.AlreadyExistentExcpetion;
import br.com.managebot.error.ResourceNorFoundException;
import br.com.managebot.model.Location;
import br.com.managebot.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("locations")
public class LocationEndpoint {
    private final LocationRepository locationDAO;

    @Autowired
    public LocationEndpoint(LocationRepository locationDAO) {
        this.locationDAO = locationDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(locationDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Long id) {
        verifyID(id);
        Optional<Location> location = locationDAO.findById(id);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping(path = "/byLocationName/{Location}")
    public ResponseEntity<?> findByLocation(@PathVariable String Location) {
        return new ResponseEntity<>(locationDAO.findByLocation(Location), HttpStatus.OK);
    }

    @GetMapping(path = "/byLocationDescription/{Description}")
    public ResponseEntity<?> findByDescription(@PathVariable String Description) {
        return new ResponseEntity<>(locationDAO.findByDescription(Description), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Location location) {
        verifyLocation(location.getLocation());
        return new ResponseEntity<>(locationDAO.save(location),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        verifyID(id);
        locationDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Location location) {
        verifyID(location.getId());
        locationDAO.save(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyID(Long id) {
        if(!locationDAO.findById(id).isPresent()) {
            throw new ResourceNorFoundException("Location not found for ID: " + id);
        }
    }

    private void verifyLocation(String location) {
        if(!locationDAO.findByLocation(location).isEmpty()) {
            throw new AlreadyExistentExcpetion("Location already reported to: " + location);
        }
    }
}
