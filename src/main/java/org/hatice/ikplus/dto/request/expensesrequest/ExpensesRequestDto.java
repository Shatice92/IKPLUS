package org.hatice.ikplus.dto.request.expensesrequest;

import java.math.BigDecimal;

public record ExpensesRequestDto(
        Long employeeId,
        BigDecimal amount,
        String description,
        String receipt
) {}