package ua.com.foxminded.accountingsystem.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.config.SecurityUtils;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? userName : "system";
    }
}
