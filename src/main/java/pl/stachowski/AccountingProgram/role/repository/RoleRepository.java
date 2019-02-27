package pl.stachowski.AccountingProgram.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.stachowski.AccountingProgram.role.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRole(String role);
}
