package org.hatice.ikplus.controller.companycontroller;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.entity.companymanagement.Company;
import org.hatice.ikplus.service.commentandnotificationservice.CommentService;
import org.hatice.ikplus.service.companyservice.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoints.COMPANY)
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;
	
	@PostMapping
	public Company createCompany(@RequestBody Company company) {
		return companyService.createCompany(company);
	}
	
	@PutMapping("/approve/{id}")
	public Company approveCompany(@PathVariable Long id) {
		return companyService.approveCompany(id);
	}
	
	@PutMapping("/reject/{id}")
	public Company rejectCompany(@PathVariable Long id) {
		return companyService.rejectCompany(id);
	}
	
	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
		return companyService.updateCompany(id, updatedCompany);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable Long id) {
		companyService.deleteCompany(id);
	}
	
	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable Long id) {
		return companyService.getCompanyById(id);
	}
	
	@GetMapping
	public List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}
	
}