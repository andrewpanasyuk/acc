package ua.com.foxminded.accountingsystem.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;


public class SalaryItemShedule {
    private static final Logger logger = LoggerFactory.getLogger(SalaryItemShedule.class);

    @Scheduled
    public void doSmthng() {
        logger.error("IMPLEMENT ME!");
    }
}
