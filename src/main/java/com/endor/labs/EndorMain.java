package com.endor.labs;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EndorMain {
    public static void main(String[] args) {
        EndorApplication.run(new SpringApplicationBuilder()
                        .bannerMode(Banner.Mode.OFF)
                        .sources(MainConfig.class),
                args);
    }
}
