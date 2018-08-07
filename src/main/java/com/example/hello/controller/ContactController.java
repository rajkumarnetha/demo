package com.example.hello.controller;

import com.example.hello.model.Contact;
import com.example.hello.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @RequestMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "contact";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String name) {
        Contact contact = new Contact();
        contact.setName(name);
        int id = ((Collection<?>) contactRepository.findAll()).size() + 1;
        contact.setId(id);
        contactRepository.save(contact);
        return "redirect:/show/" + contact.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", contactRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id) {
        Contact contact = contactRepository.findById(id).get();
        contactRepository.delete(contact);

        return "redirect:/contact";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", contactRepository.findById(id).get());
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam String name) {
        Contact contact = contactRepository.findById(id).get();
        contact.setId(id);
        contact.setName(name);
        contactRepository.save(contact);
        return "redirect:/show/" + contact.getId();
    }

}
