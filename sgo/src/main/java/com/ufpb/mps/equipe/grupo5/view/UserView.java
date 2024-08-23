package com.ufpb.mps.equipe.grupo5.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.model.User;

public class UserView {

    private final UserController userController;
    private final Scanner scanner;

    public UserView(Scanner scanner) {
        this.userController = new UserController();
        this.scanner = scanner;
    }

    public void userMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("3. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void registerUser() {
        while (true) {
            System.out.println("Digite o CPF:");
            String cpf = scanner.nextLine();
            
            System.out.println("Digite o nome:");
            String name = scanner.nextLine();

            System.out.println("Digite o e-mail:");
            String email = scanner.nextLine();

            System.out.println("Digite a matrícula:");
            String companyId = scanner.nextLine();

            System.out.println("Digite o setor:");
            String sector = scanner.nextLine();

            System.out.println("Digite o nível de acesso (ADMINISTRADOR, CHEFE_DE_SETOR, ORCAMENTISTA, COTACIONISTA):");
            String accessLevel  = scanner.nextLine();
            
            User user = new User(cpf, name, email, companyId, sector, new Date(), accessLevel , true);

            System.out.println("Qual método de persistência?");
            System.out.println("1. Coleção");
            System.out.println("2. Banco de dados");
            System.out.println("3. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> userController.registerUserCollection(user);
                case 2 -> userController.registerUserDatabase(user);
                case 3 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void listUsers() {
        List<String> users;
        while (true) {
            System.out.println("Qual base você deseja listar?");
            System.out.println("1. Coleção");
            System.out.println("2. Banco de dados");
            System.out.println("3. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> {
                    users = userController.listUsersCollection();
                    users.forEach(System.out::println);
                }
                case 2 -> {
                    users = userController.listUsersDatabase();
                    users.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
