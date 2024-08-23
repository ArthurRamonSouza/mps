package com.ufpb.mps.equipe.grupo5;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.controller.UserController;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem vindo ao SGO");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Entidade usuário");
            System.out.println("2. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> UserController.userMenu(scanner);
                case 2 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}