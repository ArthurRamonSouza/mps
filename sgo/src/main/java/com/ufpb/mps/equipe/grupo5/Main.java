package com.ufpb.mps.equipe.grupo5;

import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.view.UserView;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=============================");
            System.out.println("   Bem-vindo ao Sistema SGO   ");
            System.out.println("=============================");
            System.out.println("Escolha uma opção:");
            System.out.println("  1. Gerenciar Usuários");
            System.out.println("  2. Sair");
            System.out.println("=============================");
            System.out.print("Digite a sua escolha: ");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> {
                    UserView userView = new UserView(scanner);
                    userView.userMenu();
                }
                case 2 -> {
                    System.out.println("\nObrigado por usar o Sistema SGO!");
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nOpção inválida. Por favor, tente novamente.");
            }
        }
    }
}
