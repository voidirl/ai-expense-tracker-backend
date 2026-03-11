package com.example.expensetracker.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
@Entity
@Table(name = "expenses")
public class Expense{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Title cannot be empty")
    private String title;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    private LocalDate expenseDate;

}

