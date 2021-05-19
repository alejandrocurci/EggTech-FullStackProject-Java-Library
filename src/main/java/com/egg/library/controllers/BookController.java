package com.egg.library.controllers;

import com.egg.library.models.Author;
import com.egg.library.models.Book;
import com.egg.library.services.AuthorService;
import com.egg.library.services.BookService;
import com.egg.library.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView showBooks() {
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books", bookService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createBook() {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", new Book());
        mav.addObject("authors", authorService.findAll());
        mav.addObject("editorials", editorialService.findAll());
        mav.addObject("title", "Create Book");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/update/{isbn}")
    public ModelAndView updateBook(@PathVariable Long isbn) {
        ModelAndView mav = new ModelAndView("book-form");
        mav.addObject("book", bookService.findBookById(isbn));
        mav.addObject("authors", authorService.findAll());
        mav.addObject("editorials", editorialService.findAll());
        mav.addObject("title", "Update Book");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveBook(@RequestParam String title, @RequestParam int year,
            @RequestParam int totalCopies, @RequestParam("author") Long idAuthor,
            @RequestParam("editorial") Long idEditorial) {

        bookService.createBook(title, year, totalCopies, idAuthor, idEditorial);
        return new RedirectView("/books");
    }

    @PostMapping("/update")
    public RedirectView saveUpdateBook(@RequestParam Long isbn, @RequestParam String title,
            @RequestParam int year, @RequestParam int totalCopies, 
            @RequestParam("author") Long idAuthor, @RequestParam("editorial") Long idEditorial) {
        bookService.modify(isbn, title, year, totalCopies, idAuthor, idEditorial);
        return new RedirectView("/books");
    }

    @PostMapping("/delete/{isbn}")
    public RedirectView deleteBook(@PathVariable Long isbn) {
        bookService.deleteBook(isbn);
        return new RedirectView("/books");
    }
}
