package com.example.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    RESOURCE_NOT_FOUND("Expected resource not found"),
    NOT_ALLOWED_EXCEPTION("Action is not allowed"),
    BAD_REQUEST_EXCEPTION("Cannot perform action"),
    VALIDATION_EXCEPTION("Values submitted are wrong"),
    USER_NOT_FOUND("User not found"),
    BOOK_NOT_FOUND("Book not found"),
    ORDER_NOT_FOUND("Order not found"),
    BOOK_INSUFFICENT_STOCK("Out of stock or insufficient stock");
    private final String defaultMessage;
}
