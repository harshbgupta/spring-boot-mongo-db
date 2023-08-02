package com.programmingtechie.springbootmongodb.service;

import com.programmingtechie.springbootmongodb.model.Expense;
import com.programmingtechie.springbootmongodb.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Can not find expense by this %s", expense.getId())
                ));
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseName(expense.getExpenseName());
        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Can  not find expense by %s", name)
                ));

    }

    public Expense getExpenseByNameAndAmount(String name, String amount) {
        return expenseRepository.findByNameAndAmount(name, amount)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Can  not find expense by %s and by %s", name, amount)
                ));

    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
