package org.hatice.ikplus.service.expensesservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.dto.request.expensesrequest.UpdateExpensesRequestDto;
import org.hatice.ikplus.exception.ErrorType;
import org.hatice.ikplus.exception.IKPlusException;
import org.hatice.ikplus.mapper.expensesmapper.ExpensesMapper;
import org.hatice.ikplus.repository.expensesrepository.ExpensesRepository;
import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.hatice.ikplus.enums.expensesenums.ExpensesStatus;
import org.hatice.ikplus.dto.request.expensesrequest.AddExpensesRequestDto;
import org.hatice.ikplus.dto.response.expensesresponse.ExpensesResponse;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper expensesMapper;

    // Harcama ekleme
    public ExpensesResponse addExpense(AddExpensesRequestDto request) {
        Expenses expense = expensesMapper.fromAddExpensesRequestDto(request);
        Expenses savedExpense = expensesRepository.save(expense);
        return expensesMapper.toExpensesResponse(savedExpense);
    }

    // Harcamayı onaylama
    public ExpensesResponse approveExpense(Long expenseId) {
        return updateExpenseStatus(expenseId, ExpensesStatus.APPROVED);
    }

    // Harcamayı reddetme
    public ExpensesResponse rejectExpense(Long expenseId) {
        return updateExpenseStatus(expenseId, ExpensesStatus.REJECTED);
    }

    // Genel statü güncelleme metodu
    private ExpensesResponse updateExpenseStatus(Long expenseId, ExpensesStatus status) {
        Expenses expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new IKPlusException(ErrorType.EXPENSE_NOT_FOUND));

        expense.setStatus(status);
        expense.setUpdatedAt(LocalDate.now());
        Expenses updatedExpense = expensesRepository.save(expense);

        return expensesMapper.toExpensesResponse(updatedExpense);
    }

    // Çalışana ait harcamaları getirme
    public List<ExpensesResponse> getExpensesByEmployee(Long employeeId) {
        List<Expenses> expenses = expensesRepository.findByEmployeeId(employeeId);
        if (expenses.isEmpty()) throw new IKPlusException(ErrorType.EXPENSE_NOT_FOUND);
        return expenses.stream().map(expensesMapper::toExpensesResponse).collect(Collectors.toList());
    }

    // Tüm harcamaları listeleme
    public List<ExpensesResponse> getAllExpenses() {
        return expensesRepository.findAll().stream()
                .map(expensesMapper::toExpensesResponse)
                .collect(Collectors.toList());
    }

    // Harcama güncelleme
    public ExpensesResponse updateExpense(Long expenseId, UpdateExpensesRequestDto request) {
        Expenses expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new IKPlusException(ErrorType.EXPENSE_NOT_FOUND));

        expensesMapper.updateExpensesFromDto(request, expense);
        expense.setUpdatedAt(LocalDate.now());
        Expenses updatedExpense = expensesRepository.save(expense);

        return expensesMapper.toExpensesResponse(updatedExpense);
    }
}