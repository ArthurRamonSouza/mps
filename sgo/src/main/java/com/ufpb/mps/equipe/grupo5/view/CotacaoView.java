package com.ufpb.mps.equipe.grupo5.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.CotacaoFacade;
import com.ufpb.mps.equipe.grupo5.model.Cotacao;

public class CotacaoView {

    private final CotacaoFacade facade;
    private final Scanner scanner;

    public CotacaoView(Scanner scanner) {
        this.facade = CotacaoFacade.getInstance();
        this.scanner = scanner;
    }

    public void cotacaoMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar cotação");
            System.out.println("2. Listar todas as cotações");
            System.out.println("3. Deletar cotação");
            System.out.println("4. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerCotacao();
                case 2 -> listCotacoes();
                case 3 -> removeCotacao();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registerCotacao() {
        System.out.println("Digite a descrição da cotação:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o item:");
        String item = scanner.nextLine();

        System.out.println("Digite o valor médio:");
        double precoMedio = scanner.nextDouble();

        Cotacao cotacao = new Cotacao();
        cotacao.setDescricao(descricao);
        cotacao.setItem(item);
        cotacao.setPrecoMedio(BigDecimal.valueOf(precoMedio));

        facade.registerCotacao(cotacao);
    }

    public void listCotacoes() {
        List<Cotacao> cotacoes = facade.listCotacoes();
        System.out.printf("%d cotações foram recuperadas.%n", cotacoes.size());
        cotacoes.forEach(System.out::println);
    }

    public void removeCotacao() {
        System.out.println("Digite o ID da cotação a ser removida:");

        try {
            Long id = scanner.nextLong();
            facade.deleteCotacao(facade.findCotacaoById(id));
        } catch (Exception e) {
            System.out.println("Erro: Cotação não encontrada na base de dados.");
            scanner.nextLine();
        }
    }
}
