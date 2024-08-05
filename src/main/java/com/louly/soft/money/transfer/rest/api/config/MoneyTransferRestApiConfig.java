package com.louly.soft.money.transfer.rest.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.louly.soft.money.transfer.rest.api.dao.repository")
@EntityScan(basePackages = "com.louly.soft.money.transfer.rest.api.dao")
@ComponentScan(basePackages = {"com.louly.soft.money.transfer.rest.api"})
public class MoneyTransferRestApiConfig {
}
