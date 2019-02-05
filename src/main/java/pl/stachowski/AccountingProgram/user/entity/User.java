package pl.stachowski.AccountingProgram.user.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import pl.stachowski.AccountingProgram.company.entity.Company;
import pl.stachowski.AccountingProgram.invoice.entity.Invoice;
import pl.stachowski.AccountingProgram.role.entity.Role;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "email")
//	@Email(message = "*Please provide a valid Email")
//	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
//	@Length(min = 5, message = "*Your password must have at least 5 characters")
//	@NotEmpty(message = "*Please provide your password")
//	@Transient
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name = "active")
	private int active;
	
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Role role;
    
    private String nameOfMyComapny;
    
	private String street;
	
	private String city;
	
	private String postCode;
	
	private int taxNumber;
    
    @OneToMany
    private List<Company> company;
    
    @OneToMany
    private List<Invoice> invoice;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

	public String getNameOfMyComapny() {
		return nameOfMyComapny;
	}

	public void setNameOfMyComapny(String nameOfMyComapny) {
		this.nameOfMyComapny = nameOfMyComapny;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public int getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(int taxNumber) {
		this.taxNumber = taxNumber;
	}
}
