package com.evan.blog.pojo.management;

import com.evan.blog.model.Category;

public class CategoriesManagementListItem extends Category {
    public CategoriesManagementListItem() {
    }

    public CategoriesManagementListItem(Category category) {
        this.setId(category.getId());
        this.setName(category.getName());
        this.setCreatedTime(category.getCreatedTime());
        this.setNumberOfIncludedPubArticles(category.getNumberOfIncludedPubArticles());
    }
}
