package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
