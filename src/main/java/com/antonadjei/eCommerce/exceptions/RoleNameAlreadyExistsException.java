package com.antonadjei.eCommerce.exceptions;


public class RoleNameAlreadyExistsException extends RuntimeException{
    public RoleNameAlreadyExistsException(String message) {
        super(message);
    }

}
