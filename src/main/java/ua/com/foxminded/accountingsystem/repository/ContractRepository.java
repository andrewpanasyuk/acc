package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.Contract;


/**
 * Created by Dmytro Kushnir on 03.06.17.
 */
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
