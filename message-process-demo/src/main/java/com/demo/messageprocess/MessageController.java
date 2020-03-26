package com.demo.messageprocess;


import com.alibaba.fastjson.JSONObject;
import com.demo.messageprocess.Repository.AsyncUserRepository;
import com.demo.messageprocess.model.Event;
import com.demo.messageprocess.model.Message;
import com.demo.messageprocess.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    private final AsyncUserRepository asyncUserRepository;
  //  private final ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping()
    public Flux<Message> getAllMessage(){
        return messageService.getAllMessages();
    }


    @GetMapping("/users")
    public Flux<User> getAllUsers(){
        return asyncUserRepository.findAll();
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value = "/events")
    public Flux<Event> eventFlux(){
        Flux<Event> eventFlux = Flux.fromStream(Stream.generate(
                ()->new Event(System.currentTimeMillis(),new Date())))
                .metrics();
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(2));
        return Flux.zip(eventFlux,durationFlux).map(Tuple2::getT1);
    }



    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody JSONObject params){
        String sender = params.getString("sender");
        String  content= params.getString("content");
        String userGroup = params.getString("userGroup");
        messageService.sendMessageToGroup(sender,content,userGroup);
    }

    @PutMapping("/checkMessage/{receiver}")
    public List<Message> checkMessage(@PathVariable String receiver){
        messageService.checkMessage(receiver);
        return messageService.getMessageByUserName(receiver);
    }
}
