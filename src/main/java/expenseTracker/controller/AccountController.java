package expenseTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")

public class AccountController {
    
    private ExpenseRepository expenseRepository;

    public AccountController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Creates a mapping for every year, where an expense was made.
     * @return a mapping from Integer to BigDecimal. Only years, where an expense
     * was made, are considered.
     */
    @GetMapping("/yearMapping")
    public Map<Integer, BigDecimal> getYearlyMapBalance() {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<Integer, BigDecimal> currMap = new HashMap<Integer, BigDecimal>();

        for (Expense e : allExpenses) {
            BigDecimal amount = e.getAmount();
            int currYear = e.getDate().getYear();
            
            BigDecimal currSum = currMap.get(currYear);
            // Mapping has not existed yet
            if (currSum == null) {
                currMap.put(currYear, amount);
            }
            else {
                currMap.put(currYear, currSum.add(amount));
            }
        }

        return currMap;
    }

    /**
     * Creates a mapping for every month on a given year.
     * @return a mapping from Integer to BigDecimal. Every month is considered.
     * Months, where no expense was made, are mapped to BigDecimal.ZERO
     */ 
    @GetMapping("/monthMapping")
    public Map<Integer, BigDecimal> getMonthlyMapBalance(@RequestParam("year") int year) {
        List<Expense> allExpenses = expenseRepository.findAll();
        Map<Integer, BigDecimal> currMap = new HashMap<Integer, BigDecimal>();

        for (Expense currExpense : allExpenses) {
            BigDecimal amount = currExpense.getAmount();
            int currYear = currExpense.getDate().getYear();
            if (currYear != year) {
                continue;
            }

            int currMonth = currExpense.getDate().getMonthValue();

            BigDecimal currSum = currMap.get(currMonth);
            // Mapping has not existed yet
            if (currSum == null) {
                currMap.put(currMonth, amount);
            }
            else {
                currMap.put(currMonth, currSum.add(amount));
            }
        }

        // Creates for every non-mapped month a mapping
        for (int i = 1; i <= 12; i++) {
            BigDecimal currSum = currMap.get(i);
            if (currSum == null) {
                currMap.put(i, BigDecimal.ZERO);
            }
        }

        return currMap;
    }

    //Returns the balance of a given month
    @GetMapping("/monthBalance")
    public BigDecimal getMonthBalance(@RequestParam("date") LocalDate date) {
        List<Expense> allExpenses = expenseRepository.findAll(); 

        AccountBalance accountBalance = new AccountBalance();
        for (Expense e : allExpenses) {
            accountBalance.addExpense(e);
        }

        return accountBalance.getAmountForThisMonth(date);
    }

    // Returns the balance of a given day
    @GetMapping("/dayBalance")
    public BigDecimal getDayBalance(@RequestParam("day") LocalDate date) {
        List<Expense> allExpenses = expenseRepository.findAll(); 

        AccountBalance accountBalance = new AccountBalance();
        for (Expense e : allExpenses) {
            accountBalance.addExpense(e);
        }

        return accountBalance.getAmountForThisDay(date);
    }

}
