package com.ufpb.mps.equipe.grupo5.util.exception;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException() { super("Login inválido.");
    }

    public InvalidLoginException(String message) {
        super(message);
    }
}