package com.sanapp.sms.repository;

import com.sanapp.sms.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query(value = "select MAX(totol_invoice_generated) from invoice", nativeQuery = true)
    int lastBilledInvoice();
}
