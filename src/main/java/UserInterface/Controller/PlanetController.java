package UserInterface.Controller;

import CoreDomain.Dtos.PlanetDto;
import CoreDomain.Entity.Planet;
import CoreDomain.Services.IPlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/planets/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetController {

    private final IPlanetService planetService;

    @Autowired
    public PlanetController(IPlanetService planetService) {
        this.planetService = planetService;
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody @Valid PlanetDto planetDto) {

        Planet planet = this.planetService.save(planetDto);

        if (planetDto != null && planetDto.getId().isEmpty())
            return new ResponseEntity(planet, HttpStatus.CREATED);
        return new ResponseEntity(planet, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findAll() {
        return new ResponseEntity(this.planetService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByNome(@PathVariable String nome) {
        return new ResponseEntity(this.planetService.findByName(nome), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findById(@PathVariable String id) {
        Optional<Planet> planet = this.planetService.findById(id);

        if (planet.isPresent())
            return new ResponseEntity(planet, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable String id) {
        final String planetId = this.planetService.deleteById(id);

        if (planetId.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
