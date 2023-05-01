package com.br.library.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    String username;
    String msg;
    MsgType type;
    @Builder
    public void Message(String username, String msg, MsgType type){
        this.username = username;
        this.msg = msg;
        this.type = type;
    }
}
