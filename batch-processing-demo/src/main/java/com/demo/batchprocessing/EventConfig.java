package com.demo.batchprocessing;

import com.demo.batchprocessing.domain.Event;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Log4j2
public class EventConfig {
//    @Bean
//    WebClient client() {
//        return WebClient.create("http://localhost:8081/");
//    }
//
//    @Bean
//    ApplicationRunner runner(WebClient webClient) {
//        return args -> webClient.get()
//                .uri("/api/messages/events")
//                .retrieve()
//                .bodyToFlux(Event.class)
//                .subscribe(data -> log.info(data.toString()));
//    }
}
