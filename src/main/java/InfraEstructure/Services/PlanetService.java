package InfraEstructure.Services;

import CoreDomain.Dtos.PlanetDto;
import CoreDomain.Entity.Film;
import CoreDomain.Entity.Planet;
import CoreDomain.Interfaces.IPlanetRepository;
import CoreDomain.Services.IPlanetService;
import InfraEstructure.Utils.PlanetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService implements IPlanetService {

    private IPlanetRepository planetRepository;

    @Autowired
    public PlanetService(IPlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Optional<Planet> findById(String id) {
        Optional<Planet> planet = this.planetRepository.findById(id);
        planet.ifPresent(value -> value.setQuantidadeAparicoesEmFilmes(this.getFilmCounterFromPlanetId(id)));
        return planet;
    }

    public String deleteById(String id) {
        Optional<Planet> planet = this.planetRepository.findById(id);
        if (planet.isPresent())
            this.planetRepository.deleteById(id);
        return planet.isPresent() ? planet.get().getId() : "";
    }

    public Planet save(PlanetDto dto) {
        return this.planetRepository.save(dto.converteParaEntidade(dto));
    }

    public List<Planet> findByName(String nome) {
        List<Planet> planets = this.planetRepository.getByName(nome);
        this.updateQuantidadeAparicoesEmFilmesPlanet(planets);
        return planets;
    }

    public List<Planet> findAll() {
        List<Planet> planets = this.planetRepository.findAll();
        this.updateQuantidadeAparicoesEmFilmesPlanet(planets);
        return planets;
    }

    private List<Planet> updateQuantidadeAparicoesEmFilmesPlanet(List<Planet> planets) {
        planets.stream().forEach(planet -> planet.setQuantidadeAparicoesEmFilmes(this.getFilmCounterFromPlanetId((planet.getId()))));
        return planets;
    }

    public int getFilmCounterFromPlanetId(String planetId) {
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT, "Application");
        final HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<Film> response;

        try {
            response = restTemplate.exchange(PlanetUtils.getPlanetsResource() + planetId,
                    HttpMethod.GET,
                    httpEntity,
                    Film.class);
        } catch (RestClientResponseException e) {
            return 0;
        }
        if (response.getBody() == null)
            return 0;
        return  response.getBody().getFilms().length;
    }
}
