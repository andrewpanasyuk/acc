package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
