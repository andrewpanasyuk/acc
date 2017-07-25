package ua.com.foxminded.accountingsystem.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andreb on 24.07.17.
 */
@RestController
@RequestMapping("/api")
public class StatisticsRestController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsRestController.class);

    @GetMapping("/statistics")
    public List<Integer> getStatictics() {
        log.debug("Get statistics");
        return Arrays.asList(20, 5, 3, 8);
    }
}
