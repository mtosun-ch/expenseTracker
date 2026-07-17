package expenseTracker.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private BigDecimal amount;

    enum Category { SHOPPING, BILLS, GROCERIES, RENT, CAR, FREE_TIME }
    @Enumerated(EnumType.STRING)
    private Category category;

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

}
