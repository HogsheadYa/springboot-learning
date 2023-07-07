package com.learning.controller;

import com.learning.service.IProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fwt
 * @Date 2023/7/7 10:14
 */
@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {
    private final IProducerService producerService;



    @GetMapping("/send")
    public void send(String message){
        producerService.send(message);
    }


}
