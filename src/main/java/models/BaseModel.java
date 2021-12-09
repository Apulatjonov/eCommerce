package models;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class BaseModel {
    private final UUID id;

    {
        this.id = UUID.randomUUID();
    }
}
