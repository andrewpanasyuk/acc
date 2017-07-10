package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;
    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final OrderQueueService orderQueueService;


    @Autowired
    public AdminContractController(ContractService contractService, EmployeeService employeeService,
                                   OrderService orderService, OrderQueueService orderQueueService) {
        this.contractService = contractService;
        this.employeeService = employeeService;
        this.orderService = orderService;
        this.orderQueueService = orderQueueService;
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
        model
            .addAttribute("contract", contractService.findOne(id))
            .addAttribute("employees", employeeService.findAll());
        return "admin/contract";
    }

    @PostMapping()
    public String save(@Valid Contract contract, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "admin/contract";
        }
        contractService.save(contract);
        if (orderQueueService.findQueueByOrder(contract.getOrder()) != null) {
            orderQueueService.delete(orderQueueService.findQueueByOrder(contract.getOrder()));
        }
        return "redirect:/admin/contracts";
    }

    @GetMapping("/new")
    public String newContract(@RequestParam long orderId, Model model) {
        Contract contract = contractService.prepareNewByOrderId(orderId);
        model
            .addAttribute("contract", contract)
            .addAttribute("employees", employeeService.findAll());
        return "admin/contract";
    }

    @GetMapping("/pay")
    public String invoicesForPayment(Model model) {
        List<Invoice> invoices = contractService.prepareInvoicesForPayment();
        model
            .addAttribute("invoices", invoices);
        return "admin/invoicesForPay";
    }
}


