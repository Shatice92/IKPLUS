package org.hatice.ikplus.dto.request.expensesrequest;

import java.math.BigDecimal;

public record AddExpensesRequestDto(
        Long employeeId,
        BigDecimal amount,
        String description,
        String receipt
) {}