package com.br.library.Controller;

import com.br.library.Model.Message;
import com.br.library.Model.MsgType;
import com.br.library.Model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.sql.Timestamp;

@Controller
public class MessageController {
    @MessageMapping("/register")
    @SendTo("/topic/public")
    public OutputMessage register(Message msg) throws Exception {
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setContent(msg);
        outputMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return outputMessage;
    }

    @MessageMapping("/send")
    @SendTo("/topic/public")
    public OutputMessage sendMsg(Message msg) throws Exception {
        MsgType type = msg.getType();
        String msgSend = "";
        OutputMessage outputMessage = new OutputMessage();
        switch (type){
            case CHAT:
                msgSend = msg.getMsg();
                break;
            case LEAVE:
                msgSend = msg.getUsername() + " leave chat!";
                break;
        }

        outputMessage.setContent(new Message(msg.getUsername(), msgSend, type));
        outputMessage.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return outputMessage;
    }
}
