package com.library.vlad.Controller;

import com.library.vlad.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Controller
public class HelloController {
    BookService bookService = new BookService();


    @GetMapping("/addBook")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }


    @PostMapping("/addBook")
    public String addBookSubmit(@ModelAttribute Book book, Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("book", book);
        bookService.addBookToCloud(book);
        System.out.println(book.getName());
        return "result";
    }
/*
    @GetMapping("/showBooks")
    public String showBooks(Model model) throws ExecutionException, InterruptedException {
        ArrayList<Book> books = bookService.getBooks();
        model.getAttribute("books", books);
        return "showBooks";


    }
*/
    @RequestMapping("/")
    public String index() throws ExecutionException, InterruptedException {

        return "home";
    }

}