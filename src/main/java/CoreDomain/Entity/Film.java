package CoreDomain.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Film {

    @JsonProperty
    private String[] films;

    public String[] getFilms() {
        return films;
    }
}
