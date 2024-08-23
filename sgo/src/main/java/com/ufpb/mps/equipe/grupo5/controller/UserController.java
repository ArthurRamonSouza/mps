package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.service.UserCollectionService;
import com.ufpb.mps.equipe.grupo5.service.UserDatabaseService;

public class UserController {
    
    private static UserCollectionService userCollectionService;
    private static UserDatabaseService userDatabaseService;

    public UserController() {
        userCollectionService = new UserCollectionService();
        userDatabaseService = new UserDatabaseService();
    }

    public static void userMenu(Scanner scanner){
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registerUser(scanner);
                case 2 -> listUsers(scanner);
                case 3 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void registerUser(Scanner scanner) {
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

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> registerUserCollection(user);
                case 2 -> registerUserDatabase(user);
                case 3 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void listUsers(Scanner scanner) {
        while (true) {
            System.out.println("Qual base você deseja listar?");
            System.out.println("1. Coleção");
            System.out.println("2. Banco de dados");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            List<String> users;

            switch (opcao) {
                case 1 -> {
                    users = listUsersCollection();
                    for (String string : users) {
                        System.out.println(string);
                        
                    }
                }
                case 2 -> {
                    users = listUsersDatabase();
                    for (String string : users) {
                        System.out.println(string);
                        
                    }
                }
                case 3 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static String registerUserCollection(User user) {
        userCollectionService.save(user);
        return "O usuário foi registrado no banco de dados e na coleção.";
    }

    public static String registerUserDatabase(User user) {
        String message = "O usuário foi registrado no banco de dados.";

        try { 
            userDatabaseService.save(user);
        } catch(Error e) {
            return message = "Erro ao slavar o usuário no banco de dados.";
        }

        return message;
    }

    public static List<String> listUsersCollection() {
        List<User> usersRecovered = userCollectionService.findAll().get();
        List<String> userString = new ArrayList<>();

        for (User user : usersRecovered) {
            userString.add(user.toString());
        }

        return userString;
    }

    public static List<String> listUsersDatabase() {
        List<User> usersRecovered = userDatabaseService.findAll().get();
        List<String> userString = new ArrayList<>();

        for (User user : usersRecovered) {
            userString.add(user.toString());
        }

        return userString;
    }
}
