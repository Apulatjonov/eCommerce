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

public class CategoryService extends FileUtils<Product> implements CategoryRepository, Responses {

    public static final String categoryFileUrl = TOKEN + "categoryList.json";



    @Override
    public String add(Category category) {
        if(isExistscategory(category.getName()))
            return CATEGORY_ALREADY_EXISTS;
        List<Category> categoryList = getList(); // getting list from file
        categoryList.add(category); // adding user to list
        writeFile(categoryList); // writing list to file
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
        int index = 0;
        List<Category> list = getList();
        for (Category category : list) {
            if (category.getId().equals(id)) {
                Category editedCategory = editCategory(newCategory,category);
                list.set(index,editedCategory); // editing list
                writeFile(list); // writing to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    @Override
    public String remove(UUID id) {
        int index = 0;
        List<Category> list = getList();
        for (Category category : list) {
            if (category.getId().equals(id)) {
                list.remove(index); // removing user from list
                writeFile(list); // writing list to file
                return SUCCESS;
            }
            index++;
        }
        return USER_NOT_FOUND;
    }

    @Override
    public List<Category> getList() {
        return readFile(categoryFileUrl);
    }


    public void getCategories(List<Category> list){
        int index = 1;
        for (Category category : list) {
            System.out.println(index + ") " + category.getId() + " " + category.getName());
            index++;
        }
    }

    private boolean isExistscategory(String name) {
        for (Category category : getList()) {
            if (category.getName().equals(name))
                return true;
        }
        return false;
    }


    private Category editCategory(Category newCategory, Category oldCategory){
        oldCategory.setName(newCategory.getName());
        return oldCategory;
    }




    // Working with files
    private void writeFile(List<Category> list){
        String json = toJson(list);
        writeToFile(categoryFileUrl, json);
    }

    private List<Category> readFile(String categoryFileUrl){
        return fromJson(readFromFile(categoryFileUrl));
    }

    public List<Category> fromJson(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Category> list = new ArrayList<>();
        try {
            if(!json.equals(""))
                list = objectMapper.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String toJson(List<Category> list){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "EXCEPTION";
    }
    //working with files end

}
