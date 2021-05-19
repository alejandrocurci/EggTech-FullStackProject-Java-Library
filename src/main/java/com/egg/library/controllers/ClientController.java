
package com.egg.library.controllers;

import com.egg.library.models.Client;
import com.egg.library.services.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ModelAndView showClients() {
        ModelAndView mav = new ModelAndView("clients");
        mav.addObject("clients", clientService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView createClient() {
        ModelAndView mav = new ModelAndView("client-form");
        mav.addObject("client", new Client());
        mav.addObject("title", "Create Client");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateClient(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("client-form");
        mav.addObject("client", clientService.findClient(id));
        mav.addObject("title", "Update Client");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView saveClient(@RequestParam String firstName, @RequestParam Long document,
                                   @RequestParam String lastName, @RequestParam String phone) {
        clientService.createClient(document, firstName, lastName, phone);
        return new RedirectView("/clients");
    }

    @PostMapping("/update")
    public RedirectView saveUpdateClient(@RequestParam Long id, @RequestParam Long document,
                                         @RequestParam String firstName, @RequestParam String lastName,
                                         @RequestParam String phone) {
        clientService.modify(id, document, firstName, lastName, phone);
        return new RedirectView("/clients");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new RedirectView("/clients");
    }

}
