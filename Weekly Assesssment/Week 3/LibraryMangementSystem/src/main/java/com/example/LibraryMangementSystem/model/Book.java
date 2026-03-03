package com.example.LibraryMangementSystem.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.message.Message;

public class Book {
    int id;

    @NotBlank(message = "Title is required")
    String title;

    @NotBlank(message = "Author name is required")
    String author;

    @Positive(message = "price must be greater than 0")
    double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Author name is required") String getAuthor() {
        return author;
    }

    public void setAuthor(@NotBlank(message = "Author name is required") String author) {
        this.author = author;
    }

    @Positive(message = "price must be greater than 0")
    public double getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "price must be greater than 0") double price) {
        this.price = price;
    }
}
