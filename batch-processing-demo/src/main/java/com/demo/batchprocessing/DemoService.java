package com.demo.batchprocessing;

import com.demo.batchprocessing.domain.Message;
import com.demo.batchprocessing.domain.User;
import com.demo.batchprocessing.repository.MessageRepository;
import com.demo.batchprocessing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Log4j2
public class DemoService {
    private WebClient webClient;

    @Autowired
    private  MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public DemoService(){
        this.webClient = WebClient.create("http://localhost:8081");
    }
    void updateUserFlux(){
        Flux<User> userFlux = webClient.get()
                .uri("/api/users").retrieve().bodyToFlux(User.class);
        userRepository.saveAll(userFlux.name("employee-flux")
                .tag("key1","value1").metrics().log()
                .flatMap(user -> {
                    user.setName("ABCDE");
                    return Mono.just(user).subscribeOn(Schedulers.parallel());
                }))
                .subscribeOn(Schedulers.parallel())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }
                    @Override
                    public void onNext(User user) { }
                    @Override
                    public void onError(Throwable throwable) {
                        throwable.fillInStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        log.info("update user succeed.");
                    }
                });
    }
    void updateMessageFlux(){
        Flux<Message> messageFlux = webClient.get()
                .uri("/api/messages").retrieve().bodyToFlux(Message.class);
        messageRepository.saveAll(messageFlux
                .name("employee-flux")
                .tag("key1","value1")
                .metrics()
                .flatMap(message -> {
                    message.setContent("bulkInsert");
                    return Mono.just(message);
                }))
                .log()
                .switchIfEmpty(messageRepository.insert(messageFlux))
                .subscribe(new Subscriber<Message>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Message message) {
                        log.info(message.toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.fillInStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        log.info("update message succeed.");
                    }
                });
    }
}
