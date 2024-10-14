package com.ufpb.mps.equipe.grupo5;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.view.OrcamentoView;
import com.ufpb.mps.equipe.grupo5.view.UserView;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=============================");
                System.out.println("   Bem-vindo ao Sistema SGO   ");
                System.out.println("=============================");
                System.out.println("Escolha uma opção:");
                System.out.println("  1. Gerenciar Usuários");
                System.out.println("  2. Gerenciar Orçamentos");
                System.out.println("  3. Sair");
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
                        OrcamentoView orcamentoView = new OrcamentoView(scanner);
                        orcamentoView.orcamentoMenu();
                    }
                    case 3 -> {
                        System.out.println("\nObrigado por usar o Sistema SGO!");
                        System.out.println("Encerrando o programa...");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("\nOpção inválida. Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); 
            }
        }
    }
}
