package vn.salary.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("year", LocalDate.now().getYear());
        return "home";
    }
}
