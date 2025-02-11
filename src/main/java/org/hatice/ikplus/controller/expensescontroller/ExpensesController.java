package org.hatice.ikplus.controller.expensescontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.expensesrequest.ExpensesRequestDto;
import org.hatice.ikplus.dto.response.expensesresponse.ExpensesResponseDto;
import org.hatice.ikplus.service.expensesservice.ExpensesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.EXPENSE)
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService expensesService;

    // Harcama ekleme (POST)
    @PostMapping
    public ResponseEntity<ExpensesResponseDto> addExpense(@RequestBody ExpensesRequestDto request) {
        return ResponseEntity.ok(expensesService.addExpense(request));
    }

    // Harcamayı onaylama (PUT)
    @PutMapping("/approve/{id}")
    public ResponseEntity<ExpensesResponseDto> approveExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.approveExpense(id));
    }

    // Harcamayı reddetme (PUT)
    @PutMapping("/reject/{id}")
    public ResponseEntity<ExpensesResponseDto> rejectExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.rejectExpense(id));
    }

    // Belirli bir çalışana ait harcamaları getirme (GET)
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ExpensesResponseDto>> getExpensesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(expensesService.getExpensesByEmployee(employeeId));
    }

    // Tüm harcamaları listeleme (GET)
    @GetMapping
    public ResponseEntity<List<ExpensesResponseDto>> getAllExpenses() {
        return ResponseEntity.ok(expensesService.getAllExpenses());
    }
}