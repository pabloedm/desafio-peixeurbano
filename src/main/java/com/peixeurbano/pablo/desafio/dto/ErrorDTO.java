package com.peixeurbano.pablo.desafio.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO {

    public static final String UNEXPECTED_ERROR = "unexpected_error";

    private Date timestamp;
    private Integer status;
    private String error;
    private String messageKey;
    private String message;
    private String path;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(final String messageKey) {
        this.messageKey = messageKey;
    }
}