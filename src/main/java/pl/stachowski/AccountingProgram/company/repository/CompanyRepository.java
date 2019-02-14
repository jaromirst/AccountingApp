package pl.stachowski.AccountingProgram.company.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.stachowski.AccountingProgram.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	List<Company> findAllCompanyByUserId(long l);
	

}
