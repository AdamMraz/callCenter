package ru.adamDev.callCenter.service.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseBody <T> {
    Long timestamp;
    Integer statusCode;
    String status;
    T message;
}
