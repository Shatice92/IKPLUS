package org.hatice.ikplus.service.companyservice;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.Repository.companyrepository.CompanyRepository;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
	private final CompanyRepository companyRepository;
	
	public Company createCompany(Company company) {
		company.setApproved(false);
		return companyRepository.save(company);
	}
	
	public Company approveCompany(Long companyId) {
		Company company = companyRepository.findById(companyId).orElseThrow();
		company.setApproved(true);
		return companyRepository.save(company);
	}
	
	public Company rejectCompany(Long companyId) {
		Company company = companyRepository.findById(companyId).orElseThrow();
		company.setApproved(false);
		return companyRepository.save(company);
	}
	
	public Company updateCompany(Long companyId, Company updatedCompany) {
		Company company = companyRepository.findById(companyId).orElseThrow();
		company.setName(updatedCompany.getName());
		company.setEmailDomain(updatedCompany.getEmailDomain());
		company.setLogo(updatedCompany.getLogo());
		return companyRepository.save(company);
	}
	
	public void deleteCompany(Long companyId) {
		companyRepository.deleteById(companyId);
	}
	
	public Company getCompanyById(Long companyId) {
		return companyRepository.findById(companyId).orElseThrow();
	}
	
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
	
}