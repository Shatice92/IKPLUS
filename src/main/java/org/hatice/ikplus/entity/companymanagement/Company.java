package org.hatice.ikplus.entity.companymanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.ikplus.enums.CompanyStatus;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name; // Şirket Adı
	
	@Column(nullable = false)
	private LocalDate registrationDate; // Kayıt Tarihi
	
	@Column(nullable = false, length = 255)
	private String address; // Şirket Adresi
	
	@Column(nullable = false, length = 20)
	private String phone; // Telefon Numarası
	
	@Column(nullable = false, length = 100, unique = true)
	private String email; // E-Posta Adresi
	
	@Column(length = 255)
	private String website; // Web Sitesi
	
	@Column(length = 50)
	private String sector; // Sektör Bilgisi
	
	@Column(nullable = false, length = 20, unique = true)
	private String taxNumber; // Vergi Numarası
	
	@Column(nullable = false, length = 50)
	private String taxOffice; // Vergi Dairesi
	
	@Column(nullable = false)
	private boolean isApproved = false; // Onay Durumu
	
	@Column(nullable = false)
	private boolean emailVerified = false; // E-Posta Doğrulama
	
	@Column(nullable = false)
	private String membershipPlan; // Üyelik Planı (Aylık/Yıllık)
	
	@Column(nullable = true, length = 255)
	private String logo; // Şirket Logosu URL
	
	private String emailDomain;
	private Long companyManagerId;
	
	@Enumerated(EnumType.STRING)
	private CompanyStatus status;
	
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
}