package org.nishit.jedis.controller;

import org.nishit.jedis.sender.RedisSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class PubSubController {

    @Autowired
    private RedisSender sender;

    @GetMapping
    public String sendDataToRedisQueue(@RequestParam("redis") String input) {
        sender.sendDataToRedisQueue(input);
        return "successfully sent";
    }
}
