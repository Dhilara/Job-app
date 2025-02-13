package com.dhilara.jobapp.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhilara.jobapp.company.Company;
import com.dhilara.jobapp.company.CompanyRepository;
import com.dhilara.jobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	@Override
	public boolean updateCompany(Company updatedCompany, Long id) {
	
		Optional<Company> companyOptional = companyRepo.findById(id);
		if(companyOptional.isPresent()) {
			Company companyToUpdate = companyOptional.get();
			companyToUpdate.setName(updatedCompany.getName());
			companyToUpdate.setDescription(updatedCompany.getDescription());
			companyRepo.save(companyToUpdate);
			return true;
		}
		return false;
	}

	@Override
	public void createCompany(Company company) {
		companyRepo.save(company);
		
	}

	@Override
	public boolean deleteCompanyById(long id) {
		if(companyRepo.existsById(id)) {
			companyRepo.deleteById(id);
			return true;							
		}
			
		return false;
	}

	@Override
	public Company getCompanyById(Long id) {		
		return companyRepo.findById(id).orElse(null);
	}

}
