package com.ufpb.mps.equipe.grupo5.util;

import com.ufpb.mps.equipe.grupo5.exceptions.InvalidPasswordException;
import com.ufpb.mps.equipe.grupo5.model.User;

import java.util.regex.Pattern;

public class PasswordValidator {
    public static void validatePassword(User user) throws InvalidPasswordException {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new InvalidPasswordException("A senha não pode ser vazia.");
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 128) {
            throw new InvalidPasswordException("A senha deve ter entre 8 e 128 caracteres.");
        }

        if (user.getPassword().equalsIgnoreCase(user.getName()) || user.getPassword().equalsIgnoreCase(user.getEmail()) || user.getPassword().equalsIgnoreCase(user.getCpf())) {
            throw new InvalidPasswordException("A senha não pode ser igual ao nome, email ou CPF do usuário.");
        }

        // Expressão regular para verificar a complexidade da s
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+-=[]{}|']).{8,128}$";
        if (!Pattern.matches(regex, user.getPassword())) {
            throw new InvalidPasswordException("A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial.");
        }
    }
}

//Senha:
//Tamanho mínimo da senha é 8 caracteres e o máximo 128 caracteres.
//Não ser idêntico ao nome ou endereço de e-mail ou ao cpf
//Exigir pelo menos uma letra maiúscula do alfabeto latino (A–Z)
//Exigir pelo menos uma letra minúscula do alfabeto latino (a–z)
//Exigir pelo menos um número
//Exigir pelo menos um caractere não alfanumérico: ! @ # $ % ^ & * ( ) _ + - = [ ]{} | '





