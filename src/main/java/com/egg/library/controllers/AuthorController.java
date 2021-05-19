package com.egg.library.controllers;

import com.egg.library.models.Author;
import com.egg.library.services.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ModelAndView showAuthors() {
        ModelAndView mav = new ModelAndView("authors");
        mav.addObject("authors", authorService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createAuthor() {
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", new Author());
        mav.addObject("title", "Create Author");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateAuthor(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("author-form");
        mav.addObject("author", authorService.findAuthor(id));
        mav.addObject("title", "Update Author");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveAuthor(@RequestParam String name) {
        authorService.createAuthor(name);
        return new RedirectView("/authors");
    }

    @PostMapping("/update")
    public RedirectView saveUpdateAuthor(@RequestParam Long id, @RequestParam String name) {
        authorService.modifyName(id, name);
        return new RedirectView("/authors");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new RedirectView("/authors");
    }

}
