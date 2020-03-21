package com.demo.batchprocessing;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class Producer {
    private WebClient webClient;

    @Autowired
    private Repository repository;

    public Producer(){
        this.webClient = WebClient.create("http://localhost:8081");
    }
    void updateFlux(){
        Flux<Message> employeeFlux =webClient.get()
                .uri("/api/messages")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new ReflectiveOperationException()))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ReflectiveOperationException()))
                .bodyToFlux(Message.class);
        employeeFlux.subscribe(message->{
            message.setContent("test");
            repository.save(message).subscribe();
        });
    }
}
