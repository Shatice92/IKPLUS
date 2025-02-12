package org.hatice.ikplus.dto.request.expensesrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.expensesenums.ExpensesStatus;
import org.hatice.ikplus.enums.expensesenums.ExpensesType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExpensesRequestDto {
    private String description;  // Açıklama güncellenebilir
    private BigDecimal amount;   // Harcama tutarı değiştirilebilir
    private String receipt;      // Fatura/fiş güncellenebilir
    private ExpensesStatus status;  // Durum (Onaylandı, Reddedildi, vb.)
    private Long approvedByUserId;  // Onaylayan kişi değiştirilebilir
    private LocalDate submittedAt;  // Harcama tarihi değiştirilebilir
    private ExpensesType expenseType; // Harcama kategorisi (Yemek, Konaklama vb.)
}
