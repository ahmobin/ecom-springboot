package com.mobin.ecomspringboot.globals.utilities.seeders;

import com.mobin.ecomspringboot.v1.inventory.entity.Category;
import com.mobin.ecomspringboot.v1.inventory.entity.SubCategory;
import com.mobin.ecomspringboot.v1.inventory.repositories.CategoryRepository;
import com.mobin.ecomspringboot.v1.inventory.repositories.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySeeder {
    private final CategoryRepository categoryRepo;
    private final SubCategoryRepository subCategoryRepo;

    public Category createCategory(int iteration){
        Category category = new Category();
        category.setName("Category Name " + iteration);
        category.setSlug("category-name-"+iteration);
        return categoryRepo.save(category);
    }

    public SubCategory createSubCategory(int iteration, Category category){
        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setName("Sub Category Name " + iteration);
        subCategory.setSlug("sub-category-name-"+iteration);
        return subCategoryRepo.save(subCategory);
    }
}
