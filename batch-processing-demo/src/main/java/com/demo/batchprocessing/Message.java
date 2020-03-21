package com.demo.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Message")
public class Message {
    private String id;

    private String sender;

    private String  receiver;

    private String content;

    private State state;
}
