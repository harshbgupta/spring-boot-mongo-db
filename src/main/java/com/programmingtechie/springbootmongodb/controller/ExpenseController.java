package com.programmingtechie.springbootmongodb.controller;

import com.programmingtechie.springbootmongodb.model.Expense;
import com.programmingtechie.springbootmongodb.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Object> addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Object> updateExpense(@RequestBody Expense expense) {
        expenseService.updateExpense(expense);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpense() {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }


    @GetMapping("/{name}")
    public ResponseEntity<Expense> getExpenseByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(expenseService.getExpenseByName(name));
    }

    @GetMapping("/{name}/{amount}")
    public ResponseEntity<Expense> getExpenseByNameAndAmount(@PathVariable("name") String name, @PathVariable("amount") String amount) {
        return ResponseEntity.ok(expenseService.getExpenseByNameAndAmount(name, amount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable("id") String id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
