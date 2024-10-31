package com.ufpb.mps.equipe.grupo5.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.data.service.UserAccessDataService;
import com.ufpb.mps.equipe.grupo5.facade.UserFacade;
import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.util.template.HtmlReport;
import com.ufpb.mps.equipe.grupo5.util.template.PdfReport;

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
            System.out.println("4. Remover usuário");
            System.out.println("5. Desfazer último comando");
            System.out.println("6. Gerar relatórios");
            System.out.println("7. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> loginUser();
                case 4 -> removeUser();
                case 5 -> undo();
                case 6 -> reports();
                case 7 -> {
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
        User user = new User();
        
        System.out.println("Digite o CPF:");
        user.setCpf(scanner.nextLine());
    
        System.out.println("Digite o nome:");
        user.setName(scanner.nextLine());
    
        System.out.println("Digite o e-mail:");
        user.setEmail(scanner.nextLine());
    
        System.out.println("Digite o login:");
        user.setLogin(scanner.nextLine());
    
        System.out.println("Digite a senha:");
        user.setPassword(scanner.nextLine());
    
        System.out.println("Digite a matrícula:");
        user.setCompanyId(scanner.nextLine());
    
        System.out.println("Digite o setor:");
        user.setSector(scanner.nextLine());
    
        System.out.println("Digite o nível de acesso (ADMINISTRADOR, CHEFE_DE_SETOR, ORCAMENTISTA, COTACIONISTA):");
        user.setAccessLevel(scanner.nextLine());
        
        user.setEntryDate(new Date()); 
        user.setActive(true); 

        System.out.println("Qual método de persistência?");
        System.out.println("1. Coleção");
        System.out.println("2. Banco de dados");
        System.out.println("3. Sair");

        int in = scanner.nextInt();
        scanner.nextLine();

        switch (in) {
            case 1 -> facade.registerUserCollection(user);
            case 2 -> facade.registerUserDatabase(user);
            case 3 -> System.out.println("Saindo...");

            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public void listUsers() {
        List<User> users;
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
    
    public void removeUser() {
        System.out.println("Digite o login do usuário a ser removido:");
        String login = scanner.nextLine();

        System.out.println("Qual base você deseja remover o usuário?");
        System.out.println("1. Coleção");
        System.out.println("2. Banco de dados");
        System.out.println("3. Sair");

        try {
            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> facade.deleteUserCollection(facade.getUserCollectionByLogin(login));
                case 2 -> facade.deleteUserDatabase(facade.getUserDatabaseByLogin(login));
                case 3 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } catch (Exception e) {
            System.out.println("Erro: Entrada inválida. Tente novamente.");
            scanner.nextLine();
        }
    }

    public void reports() {
    System.out.println("Escolha o tipo de relatório que deseja gerar:");
    System.out.println("1. Relatório em PDF");
    System.out.println("2. Relatório em HTML");
    System.out.println("3. Sair");

    try {
        int in = scanner.nextInt();
        scanner.nextLine();
        UserAccessDataService userAccessDataService = new UserAccessDataService();

        switch (in) {
            case 1 -> {
                PdfReport pdfReport = new PdfReport(userAccessDataService);
                pdfReport.generateReport();
                System.out.println("Relatório PDF gerado com sucesso.");
            }
            case 2 -> {
                HtmlReport htmlReport = new HtmlReport(userAccessDataService);
                htmlReport.generateReport();
                System.out.println("Relatório HTML gerado com sucesso.");
            }
            case 3 -> System.out.println("Saindo do menu de relatórios...");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    } catch (Exception e) {
        System.out.println("Erro: Entrada inválida. Tente novamente.");
        scanner.nextLine();
    }
}
    public void undo() {
        this.facade.undo();
    }
    
}
