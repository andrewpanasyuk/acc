package ua.com.foxminded.accountingsystem.config;

import org.flywaydb.core.Flyway;
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
            flyway.setLocations("db\\prod");
            flyway.migrate();
        };
    }

    @Bean
    @Profile("dev")
    public FlywayMigrationStrategy migrateStrategy() {

        return flyway -> {
            flyway.clean();
            flyway.setLocations("db\\dev");
            flyway.migrate();
        };
    }

}
