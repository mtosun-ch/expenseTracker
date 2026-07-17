package expenseTracker.model;

import java.util.*;

public class DayAccountBalance extends AccountBalance {
    // By default, expenses are initialized to 0
    int expenses = 0;

    // Creates map from unique expense id's to the expense's cateogory
    Map<Integer, Expense.Category> catMap = new HashMap<Integer, Expense.Category>();

    /**
     * Adds a new expense to the total expense and adds map to catMap
     * 
     * @param amount the amount to be added too; must be non-negative
     * @param e the type of expense
     * 
     * @throws IllegalArgumentException if amount is negative
     */
    public void addExpense(int amount, Expense e) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        expenses = expenses + amount;
        catMap.put(e.getAndSetID(), e.getCategory());
    }
}
