package CoreDomain.Services;

import CoreDomain.Dtos.PlanetDto;
import CoreDomain.Entity.Planet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IPlanetService {
    Optional<Planet> findById(String id);
    String deleteById(String id);
    Planet save(PlanetDto planetDto);
    List<Planet> findByName(String nome);
    List<Planet> findAll();
    int getFilmCounterFromPlanetId(String planetId);
}
