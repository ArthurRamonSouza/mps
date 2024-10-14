package com.ufpb.mps.equipe.grupo5.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.UserFacade;
import com.ufpb.mps.equipe.grupo5.model.User;

public class UserView {
    
    private final UserFacade facade;
    private final Scanner scanner;

    public UserView(Scanner scanner) {
        this.facade = UserFacade.getInstance();
        this.scanner = scanner;
    }

    public void userMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("3. Logar no sistema");
            System.out.println("4. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> loginUser();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void loginUser() {
        System.out.println("Digite o login:");
        String login = scanner.nextLine();

        System.out.println("Digite a senha:");
        String password = scanner.nextLine();

        if(facade.loginUser(login, password)) {
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Login inválido.");
        }

    }

    public void registerUser() {
        System.out.println("Digite o CPF:");
        String cpf = scanner.nextLine();
        
        System.out.println("Digite o nome:");
        String name = scanner.nextLine();

        System.out.println("Digite o e-mail:");
        String email = scanner.nextLine();

        System.out.println("Digite o login:");
        String login = scanner.nextLine();

        System.out.println("Digite a senha:");
        String password = scanner.nextLine();

        System.out.println("Digite a matrícula:");
        String companyId = scanner.nextLine();

        System.out.println("Digite o setor:");
        String sector = scanner.nextLine();

        System.out.println("Digite o nível de acesso (ADMINISTRADOR, CHEFE_DE_SETOR, ORCAMENTISTA, COTACIONISTA):");
        String accessLevel  = scanner.nextLine();
        
        User user = new User(cpf, name, email, login, password, companyId, sector, new Date(), accessLevel , true);

        System.out.println("Qual método de persistência?");
        System.out.println("1. Coleção");
        System.out.println("2. Banco de dados");
        System.out.println("3. Sair");

        int in = scanner.nextInt();
        scanner.nextLine();

        switch (in) {
            case 1 -> facade.registerUserCollection(user);
            case 2 -> facade.registerUserDatabase(user);
            case 3 -> {
                System.out.println("Saindo...");
                return;
            }
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public void listUsers() {
        List<User> users;
        while (true) {
            System.out.println("Qual base você deseja listar?");
            System.out.println("1. Coleção");
            System.out.println("2. Banco de dados");
            System.out.println("3. Sair");
    
            int in = scanner.nextInt();
            scanner.nextLine();
    
            switch (in) {
                case 1 -> {
                    users = facade.listUsersCollection();
                    System.out.printf("%d usuários foram recuperados da coleção.%n", users.size());
                    users.forEach(System.out::println);
                }
                case 2 -> {
                    users = facade.listUsersDatabase();
                    System.out.printf("%d usuários foram recuperados do banco de dados.%n", users.size());
                    users.forEach(user -> System.out.println(user.toString()));
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
