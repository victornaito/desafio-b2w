package main.CoreDomain.Entity;

import org.springframework.data.annotation.Id;

public abstract class BaseEntity {

    @Id
    public String id;

    public String getId() {
        return id;
    }
}
