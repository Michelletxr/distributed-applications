package com.br.library.Model;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class OutputMessage {
    Message content;
    Timestamp timestamp;
}
