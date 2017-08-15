package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.DealQueueService;
import ua.com.foxminded.accountingsystem.service.DealService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;
    private final EmployeeService employeeService;
    private final DealService dealService;
    private final DealQueueService dealQueueService;


    @Autowired
    public AdminContractController(ContractService contractService, EmployeeService employeeService,
                                   DealService dealService, DealQueueService dealQueueService) {
        this.contractService = contractService;
        this.employeeService = employeeService;
        this.dealService = dealService;
        this.dealQueueService = dealQueueService;
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

    @PutMapping
    public String save(@Valid Contract contract, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "admin/contract";
        }
        contractService.save(contract);
        if (dealQueueService.findQueueByDeal(contract.getDeal()) != null) {
            dealQueueService.delete(dealQueueService.findQueueByDeal(contract.getDeal()));
        }
        return "redirect:/admin/contracts";
    }

    @GetMapping("/new")
    public String newContract(@RequestParam long dealId, Model model) {
        Contract contract = contractService.prepareNewByDealId(dealId);
        model
            .addAttribute("contract", contract)
            .addAttribute("employees", employeeService.findAll());
        return "admin/contract";
    }

}


