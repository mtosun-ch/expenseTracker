package expenseTracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Expense {

    Expense(BigDecimal amount, String description, Category category) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        setDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private BigDecimal amount;

    enum Category { SHOPPING, BILLS, GROCERIES, RENT, CAR, FREE_TIME }
    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate date;

    public long getUniqueID() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category; 
    }

    /**
     * Sets description of the expense
     * @param description is a string with length <= 100
     * 
     * @throws IllegalArgumentException if length of input > 100
     */
    public void setDescription(String description) {
        if (description.length() > 100) {
            throw new IllegalArgumentException();
        }

        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the amount as given in the input
     * 
     * @param amount of expense; has to be non-negative
     * 
     * @throws IllegalArgumentException if amount is negative
     */
    public void setAmount(BigDecimal amount) {
        if (amount.intValue() < 0) {
            throw new IllegalArgumentException();
        }
        
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setDate() {
        date = LocalDate.now();
    }

    public LocalDate getDate() {
        return this.date;
    }

}
