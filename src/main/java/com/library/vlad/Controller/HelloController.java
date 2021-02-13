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
        bookService.addDataToCloud(book);
        System.out.println(book.getName());
        return "home";
    }


    @GetMapping("/register")
    public String addUser( Model model)
    {
        model.addAttribute("user",new User());

        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user,Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("user",user);
        CurrentUser.setUser(user);
        bookService.addDataToCloud(user);
        return "home";
    }

    @GetMapping("/login")
    public String loginUser( Model model)
    {
        model.addAttribute("user",new User());

        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user,Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("user",user);
        CurrentUser.setUser(user);
        System.out.println(bookService.findUser(user.getEmail(),user.getPass()));

        return "home";
    }


    @GetMapping("/showBooks")
    public String showBooks(Model model) throws ExecutionException, InterruptedException {
        ArrayList<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "showBooks";

    }

    @RequestMapping("/")
    public String index() throws ExecutionException, InterruptedException {

        return "home";
    }

}