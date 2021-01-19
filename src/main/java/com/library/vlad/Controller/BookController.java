package com.library.vlad.Controller;
import com.library.vlad.Model.BookService;
import com.library.vlad.Model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;



@RestController
public class BookController {

    @Autowired
    BookService patientService;



    @GetMapping("/getBookDetails")
    public Book getBook(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return patientService.getBookDetails(name);
    }

    @PostMapping("/createBook")
    public String createBook(@RequestBody Book book ) throws InterruptedException, ExecutionException {
        return patientService.saveBookDetails(book);
    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book book  ) throws InterruptedException, ExecutionException {
        return patientService.updateBookDetails(book);
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam String name){
        return patientService.deleteBook(name);
    }
}
