package com.angelfg.pizzeria.service.exception;

public class EmailApiException extends RuntimeException {

    public EmailApiException() {
        super("Error sending email...");
    }

}
