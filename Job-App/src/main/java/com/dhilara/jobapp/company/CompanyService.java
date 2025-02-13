package com.dhilara.jobapp.company;

import java.util.List;

public interface CompanyService {
	
	List<Company> getAllCompanies();
	
	boolean updateCompany(Company updatedCompany, Long id);
	
	void createCompany(Company company);
	
	boolean deleteCompanyById(long id);
	
	Company getCompanyById(Long id);

}
