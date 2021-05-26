package com.egg.library.controllers;

import com.egg.library.models.Loan;
import com.egg.library.services.BookService;
import com.egg.library.services.ClientService;
import com.egg.library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Calendar;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView showLoans() {
        ModelAndView mav = new ModelAndView("loans");
        mav.addObject("loans", loanService.findAllLoans());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createLoan() {
        ModelAndView mav = new ModelAndView("loan-form");
        mav.addObject("loan", new Loan());
        mav.addObject("clients", clientService.findAll());
        mav.addObject("books", bookService.findAllAvailable());
        mav.addObject("title", "Create Loan");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateLoan(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("loan-form");
        mav.addObject("loan", new Loan());
        mav.addObject("clients", clientService.findAll());
        mav.addObject("books", bookService.findAll());
        mav.addObject("title", "Update Loan");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveLoan(@RequestParam("client") Long idClient,
                                 @RequestParam("book") Long idBook) {
        loanService.createLoan(idClient, idBook);
        return new RedirectView("/loans");
    }

    @PostMapping("/update")
    public RedirectView saveUpdateLoan(@RequestParam Long id, @RequestParam Calendar returnDate) {
        loanService.modify(id, returnDate);
        return new RedirectView("/loans");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return new RedirectView("/loans");
    }
}
