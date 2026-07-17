package expenseTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import expenseTracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
}
