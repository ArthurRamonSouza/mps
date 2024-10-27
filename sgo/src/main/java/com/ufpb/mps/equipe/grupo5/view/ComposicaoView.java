package com.ufpb.mps.equipe.grupo5.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.ComposicaoFacade;
import com.ufpb.mps.equipe.grupo5.model.Composicao;

public class ComposicaoView {

    private final ComposicaoFacade facade;
    private final Scanner scanner;

    public ComposicaoView(Scanner scanner) {
        this.facade = ComposicaoFacade.getInstance();
        this.scanner = scanner;
    }

    public void composicaoMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar composição");
            System.out.println("2. Listar todas as composições");
            System.out.println("3. Deletar composição");
            System.out.println("4. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerComposicao();
                case 2 -> listComposicoes();
                case 3 -> removeComposicao();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registerComposicao() {
        System.out.println("Digite a descrição da composição:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o valor total sem BDI:");
        double valorTotalSemBdi = scanner.nextDouble();

        System.out.println("Digite o valor total com BDI:");
        double valorTotalBdi = scanner.nextDouble();

        Composicao composicao = new Composicao();
        composicao.setDescricao(descricao);
        composicao.setValorTotalSemBdi(BigDecimal.valueOf(valorTotalSemBdi));
        composicao.setValorTotalBdi(BigDecimal.valueOf(valorTotalBdi));

        facade.registerComposicao(composicao);
    }

    public void listComposicoes() {
        List<Composicao> composicoes = facade.listComposicoes();
        System.out.printf("%d composições foram recuperadas.%n", composicoes.size());
        composicoes.forEach(System.out::println);
    }

    public void removeComposicao() {
        System.out.println("Digite o ID da composição a ser removida:");

        try {
            Long id = scanner.nextLong();
            facade.deleteComposicao(facade.findComposicaoById(id));
        } catch (Exception e) {
            System.out.println("Erro: Composição não encontrada na base de dados.");
            scanner.nextLine();
        }
    }
}
