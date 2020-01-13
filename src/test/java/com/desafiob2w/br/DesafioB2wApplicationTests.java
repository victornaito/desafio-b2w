package com.desafiob2w.br;

import CoreDomain.Dtos.PlanetDto;
import CoreDomain.Entity.Planet;
import CoreDomain.Interfaces.IPlanetRepository;
import InfraEstructure.Services.PlanetService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ComponentScan(basePackages = { "InfraEstructure.Services", "UserInterface.Controller", "CoreDomain.Interfaces", "CoreDomain.Services"})
@RunWith(MockitoJUnitRunner.class)
class DesafioB2wApplicationTests {

    @Mock
    private IPlanetRepository iPlanetRepository;

    @InjectMocks
    private PlanetService planetService;

    PlanetDto planetDto = new PlanetDto("1", "planet-1", "clima-1", "terreno-1");
    PlanetDto planetDto2 = new PlanetDto("12234esaefb", "planet-1", "clima-1", "terreno-1");
    Planet planet = new Planet("1", "planet-1", "clima-1", "terreno-1", 2);

    @Test
    void contextLoads() {
    }

    @BeforeEach()
    public void init() {
        when(this.iPlanetRepository.save(any(Planet.class))).thenReturn(this.planet);
        when(this.iPlanetRepository.findById(any(String.class))).thenReturn(Optional.of(this.planet));
    }

    @Test
    void buscarPlanetPorId() {
        Assert.assertEquals(Optional.of(this.planet), this.iPlanetRepository.findById("1"));
    }

    @Test
    void savePlanetPorId() {
        Assert.assertEquals(this.planet, this.iPlanetRepository.save(this.planet));
    }

    @Test
    void getAllPlanets() throws Exception {
        Mockito.when(planetService.findAll()).thenReturn(Collections.singletonList(this.planet));
        Assert.assertEquals(Collections.singletonList(this.planet), this.planetService.findAll());
    }

    @Test
    void savePlanetAndReturnPlanetId() {
        Mockito.when(planetService.save(this.planetDto)).thenReturn(this.planet);
        Planet planet = this.planetService.save(this.planetDto);
        Assert.assertEquals(this.planetDto.getId(), planet.getId());
    }

    @Test
    void getFilmCounterFromPlanet() {
        Mockito.when(planetService.save(this.planetDto)).thenReturn(this.planet);
        Planet planet = this.planetService.save(this.planetDto);
        Assert.assertEquals(2, this.planet.getQuantidadeAparicoesEmFilmes());
    }

    @Test
    void getFilmCounterFromPlanetId() throws Exception {
        final int filmCounterFromPlanetId = planetService.getFilmCounterFromPlanetId(this.planetDto.getId());
        Assert.assertEquals(5, filmCounterFromPlanetId);
    }

    @Test
    void getFilmCounterFromPlanetZero() {
        final int filmCounterFromPlanetId = planetService.getFilmCounterFromPlanetId(this.planetDto2.getId());
        Assert.assertEquals(0, filmCounterFromPlanetId);
    }

    @Test
    void findPlanetByName() {
        final List<Planet> planets = planetService.findByName(this.planetDto2.getNome());
        Assert.assertTrue(planets.stream().allMatch(s -> s.getNome().equals("planet-1")));
    }

    @Test
    void getPlanetAndPlanetHasMoreThanZeroApparitionsOnFilms() {
        final Optional<Planet> planet = planetService.findById(this.planetDto.getId());
        Assert.assertTrue(planet.get().getQuantidadeAparicoesEmFilmes() > 0);
    }

    @Test
    void getPlanetAndPlanetHasZeroApparitionsOnFilms() {
        final Optional<Planet> planet = planetService.findById(this.planetDto2.getId());
        Assert.assertTrue(planet.get().getQuantidadeAparicoesEmFilmes() == 0);
    }
}
