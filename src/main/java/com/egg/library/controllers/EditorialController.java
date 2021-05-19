package com.egg.library.controllers;

import com.egg.library.models.Editorial;
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
@RequestMapping("/editorials")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public ModelAndView showEditorials() {
        ModelAndView mav = new ModelAndView("editorials");
        mav.addObject("editorials", editorialService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createEditorial() {
        ModelAndView mav = new ModelAndView("editorial-form");
        mav.addObject("editorial", new Editorial());
        mav.addObject("title", "Create Editorial");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateEditorial(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("editorial-form");
        mav.addObject("editorial", editorialService.findEditorial(id));
        mav.addObject("title", "Update editorial");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveEditorial(@RequestParam String name) {
        editorialService.createEditorial(name);
        return new RedirectView("/editorials");
    }
    
    @PostMapping("/update")
    public RedirectView saveUpdateEditorial(@RequestParam Long id, @RequestParam String name) {
        editorialService.modifyName(id, name);
        return new RedirectView("/editorials");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteEditorial(@PathVariable Long id) {
        editorialService.deleteEditorial(id);
        return new RedirectView("/editorials");
    }
}
