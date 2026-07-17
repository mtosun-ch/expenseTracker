package expenseTracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class AccountBalance {
    // By default, total amount of expenses are initialized to 0
    BigDecimal amount = BigDecimal.ZERO;

    // Creates map from unique expense id's to its expense
    Map<Long, Expense> expenseMap = new HashMap<Long, Expense>();

    public void addExpense(Expense e) {
        updateAmount();
        expenseMap.put(e.getUniqueID(), e);
    }

    public void removeExpense(Expense e) {
        updateAmount();
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

    /**
     * Returns the expenses of a date
     * 
     * @param date of type LocalDate
     * 
     * @return Expenses, which were done at "date". Returned as a HashSet.
     */
    public Set<Expense> getExpensesForThisDay(LocalDate date) {
        Set<Expense> currExpenses = new HashSet<Expense>();
        for (Long l: expenseMap.keySet()) {
            Expense currExpense = expenseMap.get(l);

            if (currExpense.getDate() == date) {
                currExpenses.add(currExpense);
            }
        }

        return currExpenses;
    }

    /**
     * Returns the expenses of a month
     * 
     * @param date of type LocalDate
     * 
     * @return Expenses, which were done at the month, specified in "date".
     * Returned as a HashSet.
     */
    public Set<Expense> getExpensesForThisMonth(LocalDate date) {
        Set<Expense> currExpenses = new HashSet<Expense>();

        int year = date.getYear();
        int month = date.getMonthValue();
        int day = 1;

        LocalDate currDate = LocalDate.of(year, month, day);
        while (day <= 31) {
            currExpenses.addAll(getExpensesForThisDay(currDate));
            day++;
            currDate = LocalDate.of(year, month, day);
        }

        return currExpenses;
    }
}
