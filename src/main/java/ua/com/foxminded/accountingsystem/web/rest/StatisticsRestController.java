package ua.com.foxminded.accountingsystem.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.dto.ClientStatisticsDto;
import ua.com.foxminded.accountingsystem.service.dto.ServiceStatisticsDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsRestController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsRestController.class);

    private final ClientService clientService;

    @Autowired
    public StatisticsRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ClientStatisticsDto getStudentStatistics() {
        return clientService.getClientStatistics();
    }

    @GetMapping("/service")
    public List<ServiceStatisticsDto> getStatisticsByService() {
        return clientService.getServiceStatistics();
    }
}
