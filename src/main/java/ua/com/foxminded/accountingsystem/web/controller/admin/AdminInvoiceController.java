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
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;

import java.util.List;

@Controller
@RequestMapping("/admin/invoices")
public class AdminInvoiceController {

    private final InvoiceService invoiceService;
    private final ContractService contractService;

    @Autowired
    public AdminInvoiceController(InvoiceService invoiceService, ContractService contractService) {
        this.invoiceService = invoiceService;
        this.contractService = contractService;
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
        model
            .addAttribute("invoice", invoice)
            .addAttribute("payment", new Payment());
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
    public String addInvoice(@RequestParam long contractID, Model model) {
        Invoice invoice = invoiceService.createInvoiceByContractId(contractID);
        model.addAttribute("invoice", invoice);
        return "admin/invoice";
    }

    @PostMapping("/addPayment")
    public String addPayment(@ModelAttribute Payment payment) {
        invoiceService.addPayment(payment);
        return "redirect:/admin/invoices/" + payment.getInvoice().getId();
    }

    @GetMapping("/issue")
    public String prepareIssueInvoices(Model model) {
        List<Invoice> invoices = contractService.prepareIssueInvoices();
        model
            .addAttribute("invoices", invoices);
        return "admin/issueInvoices";
    }

    @PostMapping("/issue")
    public String issueInvoice(@ModelAttribute Invoice invoice) {
        invoiceService.issueInvoice(invoice);
        return "redirect:/admin/invoices/issue";
    }


    @GetMapping(value = "/debt")
    public String getDebtInvoices(Model model) {
        List<Invoice> debtInvoices = invoiceService.findDebtInvoices();
        model.addAttribute("debtInvoices", debtInvoices);
        return "admin/debt";
    }
}
