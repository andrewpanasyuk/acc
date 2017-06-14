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
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.service.ContractService;

import java.time.LocalDate;

/**
 * Created by Dmytro Kushnir on 04.06.17.
 */
@Controller
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;

    @Autowired
    public AdminContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public String getAllContracts(Model model){
        model
            .addAttribute("title", "Contract management")
            .addAttribute("contracts", contractService.getAllContracts());
        return "admin/contracts";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id){
        contractService.remove(id);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/{id}")
    public String getContract(@PathVariable Long id, Model model) {
        model.addAttribute("contract", contractService.getContractById(id));
        model.addAttribute("paymentTypeValues", PaymentType.values());
        return "admin/contract";
    }

    @PostMapping()
    public String save(@ModelAttribute Contract contract){
        contractService.save(contract);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/new")
    public String newContract(Model model){
        Contract contract = new Contract();
        contract.setContractDate(LocalDate.now());
        model.addAttribute("contract", contract);
        model.addAttribute("paymentTypeValues", PaymentType.values());
        return "admin/contract";
    }

}


