package com.ufpb.mps.equipe.grupo5.util;

import com.ufpb.mps.equipe.grupo5.exceptions.InvalidLoginException;

public class LoginValidator {
    public static void validateLogin(String login) throws InvalidLoginException {
        System.out.println(login);
        if (login == null || login.isEmpty()) {
            throw new InvalidLoginException("O login não pode ser vazio.");
        }

        if (login.length() > 12) {
            System.out.println("grande");

            throw new InvalidLoginException("O login deve ter no máximo 12 caracteres.");
        }

        if (!login.matches("[a-zA-Z]+")) {
            System.out.println("numeors");
            throw new InvalidLoginException("O login não pode conter números.");
        }
    }
}
//Login:
//máximo 12 caracteres
//não pode ser vazio
//não pode ter números
