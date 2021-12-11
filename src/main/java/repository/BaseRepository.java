package repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public interface BaseRepository<T,R,RL> {
    String TOKEN = "C:\\ecommerce\\";
    R add(T t);
    T getById(UUID id);
    R edit(UUID id, T t);
    R remove(UUID id);
    RL getList();
}
