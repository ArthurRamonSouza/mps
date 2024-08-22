package com.ufpb.mps.equipe.grupo5.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;
import com.ufpb.mps.equipe.grupo5.models.UserModel;

public class UserController {
    private List<UserModel> users;
    Scanner scanner = new Scanner(System.in);

    public UserController() {
        this.users = new ArrayList<>();
    }

    public void registerUser() {
        System.out.println("Digite o e-mail:");
        String email = scanner.nextLine();
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite a matrícula:");
        String matricula = scanner.nextLine();
        System.out.println("Digite o setor:");
        String setor = scanner.nextLine();
        System.out.println("Digite o nível de acesso (ADMINISTRADOR, CHEFE_DE_SETOR, ORCAMENTISTA, COTACIONISTA):");
        String accessLevel  = scanner.nextLine();
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        UserModel user = new UserModel(email, nome, matricula, false, true, new Date(), setor, accessLevel , cpf);
        users.add(user);
        System.out.println("Usuário adicionado com sucesso!");
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (UserModel user: users) {
                System.out.println(user);
            }
        }
    }
}
