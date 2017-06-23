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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * Created by Dmytro Kushnir on 04.06.17.
 */
@Controller
@RequestMapping("/admin/contracts")
public class AdminContractController {

    private final ContractService contractService;
    private final EmployeeService employeeService;
    private final OrderService orderService;


    @Autowired
    public AdminContractController(ContractService contractService, EmployeeService employeeService, OrderService orderService) {
        this.contractService = contractService;
        this.employeeService = employeeService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllContracts(Model model){
        model
            .addAttribute("title", "Contract management")
            .addAttribute("contracts", contractService.findAll());
        return "admin/contracts";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id){
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
    public String save(@Valid Contract contract, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "admin/contract";
        }
        contractService.save(contract);
        return "redirect:/admin/contracts";
    }

    @GetMapping("/new")
    public String newContract(@RequestParam long orderId, Model model){
        Contract contract = new Contract();
        Order order = orderService.findOne(orderId);

        Money employeeRate = new Money();
        employeeRate.setPrice(order.getService().getEmployeeRate().getPrice());
        employeeRate.setCurrency(Currency.UAH);

        Money price = new Money();
        for (Money curPrice: order.getService().getPrices()) {
            if (curPrice.getCurrency() == Currency.UAH){
                price.setPrice(curPrice.getPrice());
            }
        }
        price.setCurrency(Currency.UAH);

        contract.setOrder(order);
        contract.setContractDate(LocalDate.now());
        contract.setEmployeeRate(employeeRate);
        contract.setPrice(price);

        model
            .addAttribute("contract", contract)
            .addAttribute("employees", employeeService.findAll());
        return "admin/contract";
    }

}


