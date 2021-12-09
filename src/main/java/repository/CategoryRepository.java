package repository;

import models.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository extends BaseRepository<Category, String, List<Category>> {
    List<Category> categoryList = new ArrayList<>();


}
