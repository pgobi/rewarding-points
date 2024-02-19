package com.pgobi.rewardingpoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.pgobi.rewardingpoints.entity")
@EnableJpaRepositories(basePackages = "com.pgobi.rewardingpoints.repository")
public class RewardingPointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RewardingPointsApplication.class, args);
    }

}
