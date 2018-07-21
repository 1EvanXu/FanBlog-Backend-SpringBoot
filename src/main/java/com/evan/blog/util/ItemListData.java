package com.evan.blog.util;

import java.util.List;

public class ItemListData {
    private Integer totalNumberOfItems;
    private List items;

    public ItemListData() {}

    public ItemListData(Integer totalNumberOfItems, List items) {
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
