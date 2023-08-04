package kz.planner.phonebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import kz.planner.phonebook.models.PhoneBook;
import kz.planner.phonebook.repositories.PhoneBookRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;



@Controller
public class PhoneBookController {
    private final JdbcTemplate jdbcTemplate;



    private final PhoneBookRepository phoneBookRepository;

    @Autowired
    public PhoneBookController(JdbcTemplate jdbcTemplate, PhoneBookRepository phoneBookRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.phoneBookRepository = phoneBookRepository;
    }

    @GetMapping("/")
    public String showPhoneBook(Model model) {
        List<PhoneBook> phoneNumbers = phoneBookRepository.findAll();
        model.addAttribute("phoneNumbers", phoneNumbers);
        return "phonebook";
    }

    @GetMapping("/Rustam")
    public String rustam() {
        return "rustam";
    }

    @GetMapping("/Doszhan")
    public String doszhan() {
        return "doszhan";
    }

    @GetMapping("/addPhoneNumber")
    public String showAddPhoneNumberForm(Model model) {
        model.addAttribute("phoneNumber", new PhoneBook());
        model.addAttribute("name", "");
        return "addPhoneNumber";
    }


    @PostMapping("/addPhoneNumber")
    public String addPhoneNumber(@ModelAttribute PhoneBook phoneNumber) {
        System.out.println("Name: " + phoneNumber.getName());
        System.out.println("Phone Number: " + phoneNumber.getPhoneNumber());

        phoneNumber.setCreatedAt(new Date());
        phoneNumber.setUpdatedAt(new Date());

        phoneBookRepository.save(phoneNumber);
        return "redirect:/";
    }

    @PostMapping("/deletePhoneNumber")
    public String deletePhoneNumber(@RequestParam Long id) {
        phoneBookRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/updatePhoneNumber")
    public String updatePhoneNumber(@RequestParam Long id, @RequestParam String newPhoneNumber, @RequestParam String newName) {
        Optional<PhoneBook> phoneBookOptional = phoneBookRepository.findById(id);
        if (phoneBookOptional.isPresent()) {
            PhoneBook phoneBook = phoneBookOptional.get();
            phoneBook.setPhoneNumber(newPhoneNumber);
            phoneBook.setName(newName);
            phoneBookRepository.save(phoneBook);
        }
        return "redirect:/";
    }

    @PostMapping("/clearTable")
    public String clearTable() {
        phoneBookRepository.deleteAll();
        jdbcTemplate.update("ALTER TABLE phone_book AUTO_INCREMENT = 1");
        return "redirect:/";
    }


}
