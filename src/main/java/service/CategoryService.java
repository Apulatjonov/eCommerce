package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Category;
import models.Product;
import models.user.User;
import repository.CategoryRepository;
import repository.FileUtils;
import responses.Responses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryService extends FileUtils<Category> implements CategoryRepository, Responses {

    public static final String categoryFileUrl = TOKEN + "categoryList.json";


    @Override
    public String add(Category category) {
        write(category, categoryFileUrl);
        return SUCCESS;
    }

    @Override
    public Category getById(UUID id) {
        for (Category category : getList()) {
            if (category.getId().equals(id))
                return category;
        }
        return null;
    }

    @Override
    public String edit(UUID id, Category newCategory) {
        if(newCategory == null)
            return SOMETHING_WENT_WRONG;
        int index = 0;
        List<Category> list = getList();
        for(Category category: list) {
            if(category.getId().equals(id)) {
                Category editedCategory = editCategory(newCategory, category);
                list.set(index, editedCategory);
                writeList(list, categoryFileUrl);
                return SUCCESS;
            }
            index++;
        }
        return CATEGORY_NOT_FOUND;
    }


    @Override
    public String remove(UUID id) {
        int index = 0;
        List<Category> list = getList();
        for (Category category : list) {
            if (category.getId().equals(id)) {
                list.remove(index);
                writeList(list, categoryFileUrl); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return CATEGORY_NOT_FOUND;
    }

    @Override
    public List<Category> getList() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Category> categoryList = objectMapper.convertValue(read(categoryFileUrl), new TypeReference<List<Category>>() { });
        return categoryList;
    }

    public void getCategories(List<Category> list){
        int index = 1;
        for (Category category : list) {
            System.out.println(index + ") " + category.getId() + " " + category.getName());
            index++;
        }
    }

    private Category editCategory(Category newCategory, Category oldCategory){
        oldCategory.setName(newCategory.getName());
        return oldCategory;
    }
}
