package ua.com.foxminded.accountingsystem.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfig {

    @Bean
    @Profile("prod")
    public FlywayMigrationStrategy cleanMigrateStrategy() {

        return flyway -> {
            flyway.clean();
            flyway.migrate();
        };
    }
}
