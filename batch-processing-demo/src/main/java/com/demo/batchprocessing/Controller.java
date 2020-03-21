package com.demo.batchprocessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class Controller {

    private final Producer producer;

    @RequestMapping("/invoke")
    public String handle() throws Exception {
        log.info("invoke");
        producer.updateFlux();
        return "batch processing has been invoked";
    }
}
