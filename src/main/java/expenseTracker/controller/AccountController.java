package expenseTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import expenseTracker.model.AccountBalance;
import expenseTracker.model.Expense;
import expenseTracker.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/account")
public class AccountController {
    
    private ExpenseRepository expenseRepository;

    public AccountController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/month")
    public BigDecimal getMonthBalance(@RequestParam("date") LocalDate date) {
        List<Expense> allExpenses = expenseRepository.findAll(); 

        AccountBalance accountBalance = new AccountBalance();
        for (Expense e : allExpenses) {
            accountBalance.addExpense(e);
        }

        return accountBalance.getAmountForThisMonth(date);
    }

    @GetMapping("/day")
    public BigDecimal getDayBalance(@RequestParam("day") LocalDate date) {
        List<Expense> allExpenses = expenseRepository.findAll(); 

        AccountBalance accountBalance = new AccountBalance();
        for (Expense e : allExpenses) {
            accountBalance.addExpense(e);
        }

        return accountBalance.getAmountForThisDay(date);
    }

}
