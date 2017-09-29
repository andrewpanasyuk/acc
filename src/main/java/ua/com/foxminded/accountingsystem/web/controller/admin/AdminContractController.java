package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.DealQueueService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.exception.ActiveContractExistsException;
import javax.validation.Valid;
import ua.com.foxminded.accountingsystem.service.exception.ContractCreatingException;

@Controller
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;
    private final EmployeeService employeeService;
    private final DealQueueService dealQueueService;
    private final InvoiceService invoiceService;

    @Autowired
    public AdminContractController(ContractService contractService, EmployeeService employeeService,
                                   DealQueueService dealQueueService, InvoiceService invoiceService) {
        this.contractService = contractService;
        this.employeeService = employeeService;
        this.dealQueueService = dealQueueService;
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String getAllContracts(Model model) {
        model
            .addAttribute("title", "Contract management")
            .addAttribute("contracts", contractService.findAll());
        return "admin/contracts";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id) {
        contractService.delete(id);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/{id}")
    public String getContract(@PathVariable Long id, Model model) {
        Contract contract = contractService.findOne(id);
        model
            .addAttribute("contract", contract)
            .addAttribute("employees", employeeService.findAll())
            .addAttribute("payments", contractService.findAllRelatedPayments(contract))
            .addAttribute("invoices", invoiceService.findInvoicesByContract(contract));
        return "admin/contract";
    }

    @PutMapping
    public String save(@Valid Contract contract, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "admin/contract";
        }
        try {
            contractService.save(contract);
        } catch (ActiveContractExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/deals/" + contract.getDeal().getId();
        }
        return "redirect:/admin/contracts";
    }

    @GetMapping("/new")
    public String newContract(@RequestParam long dealId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Contract contract = contractService.prepareNewContractByDealId(dealId);
            model
                .addAttribute("contract", contract)
                .addAttribute("employees", employeeService.findAll());
        } catch (ContractCreatingException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/admin/deals/" + dealId;
        }
        return "admin/contract";
    }

    @GetMapping("/trial")
    public String getAllTrialContracts(Model model) {
        model.addAttribute("title", "Contract management").addAttribute("contracts", contractService.findAllByPaymentType(PaymentType.TRIAL));
        return "admin/contractsTrial";
    }

}
