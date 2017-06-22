package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.service.InvoiceService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/invoices")
public class AdminInvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public AdminInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getAllInvoices(Model model) {
        List<Invoice> invoices = invoiceService.findAll();
        model.addAttribute("invoices", invoices);
        return "admin/invoices";
    }

    @GetMapping(value = "/{id}")
    public String getInvoiceById(@PathVariable long id, Model model) {
        Invoice invoice = invoiceService.findById(id);
        model.addAttribute("localDate", LocalDate.now())
            .addAttribute("invoice", invoice);
        return "admin/invoice";
    }

    @PostMapping
    public String create(@ModelAttribute Invoice invoice) {
        invoiceService.save(invoice);
        return "redirect:/admin/invoices";
    }

    @DeleteMapping(value = "/{id}")
    public String removeInvoice(@PathVariable Long id) {
        invoiceService.delete(invoiceService.findById(id));
        return "redirect:/admin/invoices";
    }

    @GetMapping("/new")
    public String addInvoice(Model model) {
        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);
        return "admin/invoice";
    }
}
