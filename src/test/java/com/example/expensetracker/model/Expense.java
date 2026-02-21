package com.example.expensetracker.model;
import jakarta.persistence.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense{

    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate expense_date;

    //constructors
    public Expense(){}

    public Expense(String title, Double amount, String category, LocalDate expense_date){
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expense_date = expense_date;
    }

    //getters & setters
    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public Double getAmount(){
        return amount;
    }
    public void setAmount(Double amount){
        this.amount = amount;
    }

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public LocalDate getExpense_date(){
        return expense_date;
    }
    public void setExpense_date(LocalDate expense_date){
        this.expense_date = expense_date;
    }

}
