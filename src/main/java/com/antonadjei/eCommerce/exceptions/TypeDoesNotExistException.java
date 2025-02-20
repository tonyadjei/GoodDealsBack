package com.antonadjei.eCommerce.exceptions;

public class TypeDoesNotExistException extends RuntimeException{
    public TypeDoesNotExistException(String message) {
        super(message);
    }
}
