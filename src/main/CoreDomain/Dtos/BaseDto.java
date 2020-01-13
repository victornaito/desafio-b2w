package Dtos;

public abstract class BaseDto<DTO, Entidade> {
    public String id;

    public String getId() {
        return id;
    }

    public abstract Entidade converteParaEntidade(DTO dto);
}
