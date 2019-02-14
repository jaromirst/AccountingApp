package pl.stachowski.AccountingProgram.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import pl.stachowski.AccountingProgram.invoice.entity.Invoice;
import pl.stachowski.AccountingProgram.invoice.repository.InvoiceRepository;
import pl.stachowski.AccountingProgram.user.entity.User;
import pl.stachowski.AccountingProgram.user.repository.UserRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void saveNewInvoiceForCurrentUser(UserDetails currentUser, Invoice invoice) {
		User user = userRepository.findByEmail(currentUser.getUsername());
		invoice.setUser(user);
		invoice.setGrossValue(invoice.getNetValue() * (Invoice.VAT_VALUE));
		invoiceRepository.save(invoice);
	}
	
	public List<Invoice> findAllInvoiceForCurrentUser(UserDetails currentUser){
		return invoiceRepository.findAllByUserId(userRepository
				.findByEmail(currentUser
						.getUsername())
						.getId());
	}
	
	public Invoice findInvoiceById(long id) {	
		return invoiceRepository.findById(id).get();
	}
	
	public void saveEditInvoice(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
}
