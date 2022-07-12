package com.afarma.sellers.Dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseError
{
    private Integer status;
    private String message;
    private LocalDateTime timeStamp;
    private String error;

    public ResponseError() {}

    public ResponseError(HttpStatus status, String messageForUserToSee)
    {
        this.status = status.value();
        this.message = messageForUserToSee;
        this.timeStamp = LocalDateTime.now();
        this.error = status.getReasonPhrase();
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public LocalDateTime getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }
}
