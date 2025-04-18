package vn.salary.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.salary.calculator.model.enumeration.Type;
import vn.salary.calculator.model.request.TransactionRequest;
import vn.salary.calculator.service.TransactionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account")
    public String queryTransactionAccount(Model model,
                                          @ModelAttribute TransactionRequest request) {
        var now = LocalDate.now();
        if (request.getYear() == null) {
            request.setYear(now.getYear());
        }
        if (request.getMonth() == null) {
            request.setMonth(now.getMonthValue());
        }
        if (Objects.equals(request.getType(), "ALL")) {
            request.setType(null);
        }
        model.addAttribute("year", request.getYear());
        model.addAttribute("month", request.getMonth());
        model.addAttribute("type", request.getType());
        model.addAttribute("types", Type.account());
        model.addAttribute("transactions", transactionService.queryTransactionAccount(request));
        return "transaction_account";
    }

    @GetMapping("/account/total")
    public String transactionAccountTotal(Model model,
                                          @ModelAttribute TransactionRequest request) {
        var now = LocalDate.now();
        if (request.getYear() == null) {
            request.setYear(now.getYear());
        }
        if (request.getMonth() == null) {
            request.setMonth(now.getMonthValue());
        }
        model.addAttribute("year", request.getYear());
        model.addAttribute("month", request.getMonth());
        model.addAttribute("receiver", transactionService.sumTransactionAccount(request, List.of(Type.RECEIVER)));
        model.addAttribute("paid", transactionService.sumTransactionAccount(request, List.of(Type.PAID)));
        model.addAttribute("borrow", transactionService.sumTransactionAccount(request, List.of(Type.BORROW)));
        model.addAttribute("total", transactionService.sumTransactionAccount(request, Arrays.stream(Type.account()).toList()));
        return "account_total";
    }

    @GetMapping("/fund")
    public String queryTransactionFund(Model model,
                                       @ModelAttribute TransactionRequest request) {
        if (Objects.equals(request.getType(), "ALL")) {
            request.setType(null);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("now", formatter.format(LocalDate.now()));
        model.addAttribute("type", request.getType());
        model.addAttribute("types", Type.fund());
        model.addAttribute("transactions", transactionService.queryTransactionFund(request));
        return "transaction_fund";
    }
}
