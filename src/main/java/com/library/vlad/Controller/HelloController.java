package com.library.vlad.Controller;

import com.library.vlad.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping("/")
    public String index() throws ExecutionException, InterruptedException {


        Book book = new Book("Padurea Spanzuratilor", "Liviu Rebreanu");
        bookService.addBookToCloud(book);
        return "home";
    }

}