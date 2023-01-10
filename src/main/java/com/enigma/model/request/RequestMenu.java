package com.enigma.model.request;

import com.enigma.utils.Category;

public class RequestMenu {
    private String title;
    private Integer price;
    private Category category;

    @Override
    public String toString() {
        return "RequestMenu{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
