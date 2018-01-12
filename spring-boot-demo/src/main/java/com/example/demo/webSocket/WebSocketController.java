package com.example.demo.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
	
    @MessageMapping("/welcome")//1
    @SendTo("/topic/getResponse")//2
    public WebSocketMessage say(WebSocketMessage message) throws Exception {
    	System.out.println("收到msg：" + message);
        return message;
    }
}
