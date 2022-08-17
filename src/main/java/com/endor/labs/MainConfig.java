package com.endor.labs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.endor.labs"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.endor.labs.config.*"})})
public class MainConfig {
}
