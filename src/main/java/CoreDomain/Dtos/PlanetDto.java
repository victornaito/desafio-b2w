package CoreDomain.Dtos;

import CoreDomain.Entity.Planet;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class PlanetDto extends BaseDto<PlanetDto, Planet> {


    @NotBlank(message = "{nome.not.blank}")
    private String nome;
    @NotBlank(message = "{terreno.not.blank}")
    private String terreno;
    @NotBlank(message = "{nome.not.blank}")
    private String clima;

    public PlanetDto(String id, String nome, String terreno, String clima) {
        this.id = id;
        this.nome = nome;
        this.terreno = terreno;
        this.clima = clima;
    }

    public String getId() {
        return this.id;
    }

    public String getClima() {
        return clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Planet converteParaEntidade(PlanetDto dto) {
        return !dto.getId().isEmpty() ? new Planet(dto.getId(), dto.getNome(), dto.getClima(), dto.getTerreno()) :
                new Planet(dto.getNome(), dto.getClima(), dto.getTerreno());
    }
}
