package pl.stachowski.AccountingProgram.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.stachowski.AccountingProgram.role.entity.Role;
import pl.stachowski.AccountingProgram.role.enums.Roles;
import pl.stachowski.AccountingProgram.role.repository.RoleRepository;
import pl.stachowski.AccountingProgram.user.entity.User;
import pl.stachowski.AccountingProgram.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findCurrentLogUser(UserDetails currentUser) {
		User user = userRepository.findByEmail(currentUser.getUsername());
		return user;
	}
	
	public User findUserById(long id) {
		return userRepository.findById(id).get();
	}
	
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = new Role();
		userRole.setUser(user);
		userRole.setRole(String.valueOf(Roles.ROLE_USER));
		user.setActive(1);
		userRepository.saveAndFlush(user);
		roleRepository.saveAndFlush(userRole);	
	}
	
	public void editUser(User user, UserDetails currentUser) {
		User userToEdit = userRepository.findByEmail(currentUser.getUsername());
		userToEdit.setName(user.getName());
		userToEdit.setLastName(user.getLastName());
		userToEdit.setCity(user.getCity());
		userToEdit.setNameOfMyComapny(user.getNameOfMyComapny());
		userToEdit.setStreet(user.getStreet());
		userToEdit.setPostCode(user.getPostCode());
		userToEdit.setTaxNumber(user.getTaxNumber());
		userRepository.save(userToEdit);
	}
}
