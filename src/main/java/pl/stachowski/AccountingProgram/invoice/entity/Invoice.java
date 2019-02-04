package pl.stachowski.AccountingProgram.invoice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import pl.stachowski.AccountingProgram.company.entity.Company;
import pl.stachowski.AccountingProgram.user.entity.User;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Invoice{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String number;
	
	private String name;
	
	private double grossValue;
	
	private double netValue;
	
	private boolean payment;
	
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@OneToOne
	private Company company;
	
	@ManyToOne
	private User user;
	
	public static final double VAT_VALUE = 1.23;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getGrossValue() {
		return grossValue;
	}

	public void setGrossValue(double grossValue) {
		this.grossValue = grossValue;
	}

	public double getNetValue() {
		return netValue;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
