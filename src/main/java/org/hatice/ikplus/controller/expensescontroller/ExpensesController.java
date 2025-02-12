package org.hatice.ikplus.controller.expensescontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.dto.request.expensesrequest.AddExpensesRequestDto;
import org.hatice.ikplus.dto.response.BaseResponse;
import org.hatice.ikplus.dto.response.expensesresponse.ExpensesResponse;
import org.hatice.ikplus.service.expensesservice.ExpensesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.hatice.ikplus.constant.Endpoints.*;

@RestController
@RequestMapping(Endpoints.EXPENSES)
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService expensesService;

    // Harcama ekleme (POST)
    @PostMapping(SAVE)
    public ResponseEntity<BaseResponse<Boolean>> addExpense(@RequestBody AddExpensesRequestDto request) {
        expensesService.addExpense(request);
        return ResponseEntity.ok(BaseResponse.<Boolean>builder().data(true).message("Expenses saved successfully")
                .code(200).success(true).build());
    }

    // Harcamayı onaylama (PUT)
    @PutMapping(APPROVE)
    public ResponseEntity<ExpensesResponse> approveExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.approveExpense(id));
    }

    // Harcamayı reddetme (PUT)
    @PutMapping(REJECT)
    public ResponseEntity<ExpensesResponse> rejectExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expensesService.rejectExpense(id));
    }

    // Belirli bir çalışana ait harcamaları getirme (GET)
    @GetMapping(GETEXPENSESBYEMPLOYEEID)
    public ResponseEntity<List<ExpensesResponse>> getExpensesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(expensesService.getExpensesByEmployee(employeeId));
    }

    // Tüm harcamaları listeleme (GET)
    @GetMapping(LIST)
    public ResponseEntity<List<ExpensesResponse>> getAllExpenses() {
        return ResponseEntity.ok(expensesService.getAllExpenses());
    }
}