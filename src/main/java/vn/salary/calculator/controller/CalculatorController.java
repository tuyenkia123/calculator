package vn.salary.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.salary.calculator.model.enumeration.FundCategory;
import vn.salary.calculator.model.enumeration.Type;
import vn.salary.calculator.model.request.CalculatorAccountRequest;
import vn.salary.calculator.service.TransactionService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private final TransactionService transactionService;

    public CalculatorController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/form-account")
    public String formAccount(Model model) {
        model.addAttribute("types", Type.account());
        return "calculator_account";
    }

    @PostMapping("/account")
    public String account(@RequestBody List<CalculatorAccountRequest> requests) {
        transactionService.saveAll(requests);
        return "redirect:calculator/form-account";
    }

    @GetMapping("/form-fund")
    public String formFund(Model model) {
        model.addAttribute("types", Type.fund());
        return "calculator_fund";
    }

    @PostMapping("/fund")
    public String fund(@RequestBody List<CalculatorAccountRequest> requests) {
        transactionService.saveAll(requests);
        return "redirect:calculator/form-fund";
    }
}
