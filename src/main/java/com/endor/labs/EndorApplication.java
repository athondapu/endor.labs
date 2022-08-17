package com.endor.labs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;

public class EndorApplication {
    private static final Logger logger;

    private EndorApplication() {
    }

    public static ApplicationContext run(final SpringApplicationBuilder springApplicationBuilder, final String[] args) {
        return (ApplicationContext) springApplicationBuilder.bannerMode(Banner.Mode.OFF).initializers(new ApplicationContextInitializer[]{}).build().run(args);
    }

    static {
        logger = LoggerFactory.getLogger((Class) EndorApplication.class);
    }
}