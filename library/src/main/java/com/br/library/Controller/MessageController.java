package com.br.library.Controller;

import com.br.library.Model.Message;
import com.br.library.Model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message msg) throws Exception {
        return new OutputMessage();
    }
}
