package org.hatice.ikplus.service.expensesservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.expensesrepository.ExpensesRepository;
import org.hatice.ikplus.entity.expensemanagement.Expenses;
import org.hatice.ikplus.enums.expensesenums.ExpensesStatus;
import org.hatice.ikplus.dto.request.expensesrequest.ExpensesRequestDto;
import org.hatice.ikplus.dto.response.expensesresponse.ExpensesResponseDto;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final ExpensesRepository expensesRepository;

    // Harcama ekleme
    public ExpensesResponseDto addExpense(ExpensesRequestDto request) {
        Expenses expense = Expenses.builder()
                .employeeId(request.employeeId()) // Record olduğu için doğrudan erişiyoruz.
                .amount(request.amount())
                .description(request.description())
                .receipt(request.receipt())
                .status(ExpensesStatus.SUBMITTED)
                .submittedAt(LocalDate.now())
                .build();

        expense = expensesRepository.save(expense); // Veritabanına kaydet

        return mapToResponseDTO(expense);
    }

    // Harcamayı onaylama
    public ExpensesResponseDto approveExpense(Long expenseId) {
        Expenses expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(ExpensesStatus.APPROVED);
        expense.setUpdatedAt(LocalDate.now());
        expense = expensesRepository.save(expense);

        return mapToResponseDTO(expense);
    }

    // Harcamayı reddetme
    public ExpensesResponseDto rejectExpense(Long expenseId) {
        Expenses expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setStatus(ExpensesStatus.REJECTED);
        expense.setUpdatedAt(LocalDate.now());

        expense = expensesRepository.save(expense);

        return mapToResponseDTO(expense);
    }

    // Çalışana ait harcamaları getirme
    public List<ExpensesResponseDto> getExpensesByEmployee(Long employeeId) {
        return expensesRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Tüm harcamaları listeleme
    public List<ExpensesResponseDto> getAllExpenses() {
        return expensesRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Entity'den DTO'ya çevirme metodu
    private ExpensesResponseDto mapToResponseDTO(Expenses expense) {
        return new ExpensesResponseDto(
                expense.getId(),
                expense.getEmployeeId(),
                expense.getAmount(),
                expense.getDescription(),
                expense.getReceipt(),
                expense.getStatus(),
                expense.getSubmittedAt(),
                expense.getApprovedByUserId(),
                expense.getUpdatedAt()
        );
    }
}