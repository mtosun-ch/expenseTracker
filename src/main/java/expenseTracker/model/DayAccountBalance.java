package expenseTracker.model;

import java.math.BigDecimal;
import java.util.*;

public class DayAccountBalance extends AccountBalance {
    // By default, total amount of expenses are initialized to 0
    BigDecimal amount = BigDecimal.ZERO;

    // Creates map from unique expense id's to its expense
    Map<Long, Expense> expenseMap = new HashMap<Long, Expense>();

    public void addExpense(Expense e) {
        expenseMap.put(e.getUniqueID(), e);
    }

    public void removeExpense(Expense e) {
        expenseMap.remove(e.getUniqueID());
    }

    public void updateAmount() {
        BigDecimal currAmount = BigDecimal.ZERO;

        for (Long l : expenseMap.keySet()) {
            Expense currExpense = expenseMap.get(l);
            currAmount = currAmount.add(currExpense.getAmount());
        }

        amount = currAmount;
    }
}
