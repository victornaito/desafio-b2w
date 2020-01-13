package CoreDomain.Interfaces;

import CoreDomain.Entity.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlanetRepository extends MongoRepository<Planet, String> {
    @Query("nome: ?0")
    public List<Planet> getByName(String nome);
}
