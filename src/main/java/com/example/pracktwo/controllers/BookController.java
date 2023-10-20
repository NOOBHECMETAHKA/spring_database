package com.example.pracktwo.controllers;

import com.example.pracktwo.CRUDFunctions;
import com.example.pracktwo.models.Book;
import com.example.pracktwo.repo.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    String books(Model model, @ModelAttribute("book") Book book){
        List<Book> books = bookRepository.findAll();

        //Filter
        if(book.getTitle() != null && !Objects.equals(book.getTitle(), "")){
            books = books.stream().filter(x -> Objects.equals(x.getTitle(), book.getTitle())).toList();
        }

        if(book.getDatePublic() != null){
            books = books.stream().filter(x -> Objects.equals(x.getDatePublic(), book.getDatePublic())).toList();
        }

        if(book.getDescription() != null && !Objects.equals(book.getDescription(), "")){
            books = books.stream().filter(x -> Objects.equals(x.getDescription(), book.getDescription())).toList();
        }
        //Filter

        model.addAttribute("title", "Книги");
        model.addAttribute("books", books);
        model.addAttribute("book", new Book());
        return "pages/book/index";
    }

    @GetMapping("books/create")
    String create(Model model){
        model.addAttribute("title", "Создание книги");
        model.addAttribute("book", new Book());
        return "pages/book/add";
    }

    @PostMapping("books/store")
    String store(Model model, @Valid @ModelAttribute("book") Book book){
        CRUDFunctions crud = new CRUDFunctions(bookRepository);
        crud.manipulation(book, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/books";
    }

    @GetMapping("books/edit/{id}")
    String edit(Model model, @PathVariable("id") long id){
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return "redirect:/books";
        }
        model.addAttribute("bookPARAMS", book);
        model.addAttribute("title", "Создание книги");
        return "pages/book/update";
    }

    @PostMapping("books/update/{id}")
    String update(Model model, @PathVariable("id") long id, @Valid @ModelAttribute("book") Book book){
        book.setId(id);
        CRUDFunctions crud = new CRUDFunctions(bookRepository);
        crud.manipulation(book, CRUDFunctions.actionDataObject.INSERTORUPDATE);
        return "redirect:/books";
    }

    @PostMapping("books/delete/{id}")
    String delete(Model model, @PathVariable("id") long id){
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return "redirect:/books";
        }
        CRUDFunctions crud = new CRUDFunctions(bookRepository);
        crud.manipulation(book, CRUDFunctions.actionDataObject.DELETE);
        return "redirect:/books";
    }

}
