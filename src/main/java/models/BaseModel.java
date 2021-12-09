package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
public abstract class BaseModel {
    private final UUID id;

    {
        this.id = UUID.randomUUID();
    }
}
