package com.thezlotnicks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class QueueConfig {
    private Environment environment;

    @Autowired
    public QueueConfig(Environment environment) { this.environment = environment; }

    public String getQueueURL() { return environment.getProperty("QUEUE_URL"); }

    public String getRegion() { return environment.getProperty("REGION"); }
}
