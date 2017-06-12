package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.foxminded.accountingsystem.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

    UserRole findByRole(String role);
}
