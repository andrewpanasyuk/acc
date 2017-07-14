package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.Salary;
import ua.com.foxminded.accountingsystem.model.SalaryItem;
import ua.com.foxminded.accountingsystem.repository.EmployeeRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryItemRepository;
import ua.com.foxminded.accountingsystem.repository.SalaryRepository;
import ua.com.foxminded.accountingsystem.service.SalaryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SalaryServiceJPA implements SalaryService {

    private static final Logger log = LoggerFactory.getLogger(SalaryServiceJPA.class);

    private final SalaryItemRepository salaryItemRepository;
    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryServiceJPA(SalaryRepository salaryRepository, SalaryItemRepository salaryItemRepository,
                            EmployeeRepository employeeRepository){
        this.salaryRepository = salaryRepository;
        this.salaryItemRepository = salaryItemRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Salary> prepareSalaries(LocalDate dateFrom, LocalDate dateTo) {
        List<SalaryItem> salaryItems = salaryItemRepository
            .findByAccountedFalseAndDateFromGreaterThanEqualAndDateToLessThanEqual(dateFrom, dateTo);
        List<Salary> preparedSalaries = new ArrayList<>();
        HashMap<Employee, Salary> salariesForEmployee = new HashMap<>();
        for(SalaryItem salaryItem: salaryItems){
            if(!salariesForEmployee.containsKey(salaryItem.getEmployee())){
                Salary salary = new Salary();
                salary.setSalaryDate(LocalDate.now());
                salary.setDateFrom(dateFrom);
                salary.setDateTo(dateTo);
                salary.setEmployee(salaryItem.getEmployee());
                salary.addSalaryItem(salaryItem);

                salariesForEmployee.put(salaryItem.getEmployee(), salary);
                preparedSalaries.add(salary);
            }

            else{
                salariesForEmployee.get(salaryItem.getEmployee()).addSalaryItem(salaryItem);
            }
        }
        return preparedSalaries;
    }

    @Override
    public Salary create(Salary salary) {
        List<SalaryItem> salaryItems = new ArrayList<>();
        for(SalaryItem salaryItem: salary.getSalaryItems()){
            SalaryItem accountedSalaryItem = salaryItemRepository.getOne(salaryItem.getId());
            accountedSalaryItem.setAccounted(true);
            salaryItems.add(accountedSalaryItem);
        }
        salary.setSalaryItems(salaryItems);
        Salary persistedSalary = salaryRepository.save(salary);
        log.info("Salary saved: {}", persistedSalary);
        return persistedSalary;
    }
}
