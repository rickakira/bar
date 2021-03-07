package com.teste.bar.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorMessage {

    private int statusCode;
    private String message;
    private String description;
}
