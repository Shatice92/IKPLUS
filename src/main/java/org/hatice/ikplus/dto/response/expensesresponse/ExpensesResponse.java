package org.hatice.ikplus.dto.response.expensesresponse;

import org.hatice.ikplus.enums.expensesenums.ExpensesStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpensesResponse(
        Long id,
        Long employeeId,
        BigDecimal amount,
        String description,
        String receipt,
        ExpensesStatus status,
        LocalDate submittedAt,
        Long approvedByUserId,
        LocalDate updatedAt
) {}