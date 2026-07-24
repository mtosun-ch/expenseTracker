package expenseTracker.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import expenseTracker.model.Expense;
import expenseTracker.repository.ExpenseRepository;

import java.util.*;

@RestController
@RequestMapping("/api/expenses")

public class ExpenseController {

    private ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @PostMapping()
    public String saveExpense(@RequestBody Expense e) {
        Expense saved = expenseRepository.save(e);
        return "Expense with ID " + saved.getUniqueID() + " added successfully.";
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable("id") long id) {
        expenseRepository.deleteById(id);
        return "Expense with ID " + id + " deleted successfully.";
    }

    @PutMapping("/{id}")
    public String updateExpense(@PathVariable("id") long id, @RequestBody Expense e) {
        try {
            Expense currExpense = expenseRepository.findById(id).get();
            expenseRepository.save(currExpense);
            return "Expense with ID " + id + " updated successfully.";
        } catch(Exception e1) {
            return "Expense with ID " + id + " does not exist.";
        }
    }

}
