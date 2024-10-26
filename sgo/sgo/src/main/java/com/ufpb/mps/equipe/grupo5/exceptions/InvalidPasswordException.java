package com.ufpb.mps.equipe.grupo5.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException() { super("Senha inválida.");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
