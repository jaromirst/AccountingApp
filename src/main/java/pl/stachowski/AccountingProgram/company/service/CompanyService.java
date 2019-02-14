package pl.stachowski.AccountingProgram.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pl.stachowski.AccountingProgram.company.entity.Company;
import pl.stachowski.AccountingProgram.company.repository.CompanyRepository;
import pl.stachowski.AccountingProgram.user.entity.User;
import pl.stachowski.AccountingProgram.user.repository.UserRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void save(UserDetails currentUser, Company company) {
		company.setUser(userRepository.findByEmail(currentUser.getUsername()));
		companyRepository.save(company);
	}
	
	public List<Company> loadAllCompanies(UserDetails currentUser){
		User user = userRepository.findByEmail(currentUser.getUsername());
		return companyRepository.findAllCompanyByUserId(user.getId());
	}
}
