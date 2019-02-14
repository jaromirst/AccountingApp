package pl.stachowski.AccountingProgram.invoice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.stachowski.AccountingProgram.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public List<Invoice> findAllByUserId(long id);
}
