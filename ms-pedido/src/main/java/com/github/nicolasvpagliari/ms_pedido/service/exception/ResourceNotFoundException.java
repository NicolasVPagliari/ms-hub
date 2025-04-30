package com.github.nicolasvpagliari.ms_pedido.service.exception;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
