package com.example.LibraryMangementSystem.controller;


import jakarta.validation.Valid;
import com.example.LibraryMangementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.LibraryMangementSystem.service.BookService;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/add")
    public String showAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult result) {
        if (result.hasErrors()) return "add-book";
        bookService.addBook(book);
        return "redirect:/books"; // PRG pattern
    }

    @GetMapping("/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "view-books";
    }

    @GetMapping("/books/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book-details";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}