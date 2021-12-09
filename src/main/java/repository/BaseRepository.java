package repository;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<T,R,RL> {
    String TOKEN = "C:\\ecommerce\\";
    R add(T t);
    T getById(UUID id);
    R edit(UUID id, T t);
    R remove(UUID id);
    RL getList();

    default T check(List<T> list, T object){
        for (T t : list) {
            if (t.equals(object))
                return t;
        }
        return null;
    }
}
