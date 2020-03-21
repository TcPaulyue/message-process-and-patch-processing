package com.demo.messageprocess;


import com.demo.messageprocess.model.Message;
import com.demo.messageprocess.model.State;
import com.demo.messageprocess.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Flux<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Flux<Message> getMessageByUserName(String user){
        return messageRepository.findAllByReceiver(user);
    }

    public void checkMessage(String receiver){
        Flux<Message> messageFlux = messageRepository.findAllByReceiverAndState(receiver, State.UNCHECKED);
        messageFlux.subscribe(message -> {
            message.setState(State.CHECKED);
            messageRepository.save(message);
        });
    }

    public void sendMessageToGroup(String sender, String  content, String userGroup){
        List<User> users = userRepository.findAllByUserGroup(userGroup);
        users.forEach(user -> {
            Message message = new Message();
            message.setState(State.UNCHECKED);
            message.setSender(sender);
            message.setReceiver(user.getName());
            message.setContent(content);
            messageRepository.save(message);
        });
    }
}
