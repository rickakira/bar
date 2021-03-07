package com.teste.bar.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7011486017105432741L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
