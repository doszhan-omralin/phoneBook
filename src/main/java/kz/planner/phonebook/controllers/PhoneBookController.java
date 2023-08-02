package kz.planner.phonebook.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhoneBookController {
    @GetMapping("/")
    public String phonebook() {
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
}
