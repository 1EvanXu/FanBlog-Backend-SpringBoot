package com.evan.blog.pojo;

import java.util.List;

public class ItemCollection {
    private Integer totalNumberOfItems;
    private List items;

    public ItemCollection() {}

    public ItemCollection(Integer totalNumberOfItems, List items) {
        this.totalNumberOfItems = totalNumberOfItems;
        this.items = items;
    }

    public Integer getTotalNumberOfItems() {
        return totalNumberOfItems;
    }

    public void setTotalNumberOfItems(Integer totalNumberOfItems) {
        this.totalNumberOfItems = totalNumberOfItems;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
