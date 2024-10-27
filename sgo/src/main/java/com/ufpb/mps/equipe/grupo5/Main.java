package com.ufpb.mps.equipe.grupo5;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.view.OrcamentoView;
import com.ufpb.mps.equipe.grupo5.view.UserView;
import com.ufpb.mps.equipe.grupo5.view.InsumoView;
import com.ufpb.mps.equipe.grupo5.view.ComposicaoView;
import com.ufpb.mps.equipe.grupo5.view.CotacaoView;
import com.ufpb.mps.equipe.grupo5.view.EtapaView;

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
                System.out.println("  3. Gerenciar Insumos");
                System.out.println("  4. Gerenciar Composições");
                System.out.println("  5. Gerenciar Cotações");
                System.out.println("  6. Gerenciar Etapas");
                System.out.println("  7. Sair");
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
                        InsumoView insumoView = new InsumoView(scanner);
                        insumoView.insumoMenu();
                    }
                    case 4 -> {
                        ComposicaoView composicaoView = new ComposicaoView(scanner);
                        composicaoView.composicaoMenu();
                    }
                    case 5 -> {
                        CotacaoView cotacaoView = new CotacaoView(scanner);
                        cotacaoView.cotacaoMenu();
                    }
                    case 6 -> {
                        EtapaView etapaView = new EtapaView(scanner);
                        etapaView.etapaMenu();
                    }
                    case 7 -> {
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
