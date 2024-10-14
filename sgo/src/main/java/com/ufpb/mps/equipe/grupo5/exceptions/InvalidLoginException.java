package com.ufpb.mps.equipe.grupo5.exceptions;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException() { super("Login inválido.");
    }

    public InvalidLoginException(String message) {
        super(message);
    }
}