package Services;

import Dtos.PlanetDto;
import Entity.Planet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPlanetService {
    Optional<Planet> findById(String id);
    String deleteById(String id);
    Planet save(PlanetDto planetDto);
    List<Planet> findByName(String nome);
    List<Planet> findAll();
    int getFilmCounterFromPlanetId(String planetId);
}
