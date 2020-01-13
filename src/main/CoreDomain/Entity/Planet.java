package Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planet")
public class Planet extends main.CoreDomain.Entity.BaseEntity {

    @JsonProperty
    private String nome;
    @JsonProperty
    private String terreno;
    @JsonProperty
    private String clima;
    @JsonProperty
    private int quantidadeAparicoesEmFilmes;

    public Planet() {}

    public Planet(String id, String nome, String clima, String terreno) {
        super();
        this.id = id;
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public Planet(String nome, String clima, String terreno) {
        super();
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
    }

    public String getNome() {
        return nome;
    }

    public String getTerreno() {
        return terreno;
    }

    public String getClima() {
        return clima;
    }

    public int getQuantidadeAparicoesEmFilmes() {
        return quantidadeAparicoesEmFilmes;
    }

    public String getId() { return this.id; }

    public void setQuantidadeAparicoesEmFilmes(int filmCounterFromPlanetId) {
        this.quantidadeAparicoesEmFilmes = filmCounterFromPlanetId;
    }
}