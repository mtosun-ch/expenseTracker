package expenseTracker.model;

import java.util.*;

public class DayAccountBalance extends AccountBalance {
    // By default, expenses are initialized to 0
    int expenses = 0;

    // Creates map from unique expense id's to its expense
    Map<Long, Expense> expenseMap = new HashMap<Long, Expense>();

    public void addExpense(Expense e) {
        expenseMap.put(e.getUniqueID(), e);
    }

    public void removeExpense(Expense e) {
        expenseMap.remove(e.getUniqueID());
    }
}
