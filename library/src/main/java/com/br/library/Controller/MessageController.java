package com.br.library.Controller;

import com.br.library.Model.Message;
import com.br.library.Model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/chat/info")
    @SendTo("/topic/messages")
    public void send(Message msg) throws Exception {
        System.out.println("ola");
    }
}
