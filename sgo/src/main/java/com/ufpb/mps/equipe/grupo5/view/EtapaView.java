package com.ufpb.mps.equipe.grupo5.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.EtapaFacade;
import com.ufpb.mps.equipe.grupo5.model.Etapa;

public class EtapaView {

    private final EtapaFacade facade;
    private final Scanner scanner;

    public EtapaView(Scanner scanner) {
        this.facade = EtapaFacade.getInstance();
        this.scanner = scanner;
    }

    public void etapaMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar etapa");
            System.out.println("2. Listar todas as etapas");
            System.out.println("3. Deletar etapa");
            System.out.println("4. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerEtapa();
                case 2 -> listEtapas();
                case 3 -> removeEtapa();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registerEtapa() {
        System.out.println("Digite a descrição da etapa:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o valor total sem BDI:");
        double valorTotalSemBdi = scanner.nextDouble();

        System.out.println("Digite o valor total com BDI:");
        double valorTotalComBdi = scanner.nextDouble();

        Etapa etapa = new Etapa();
        etapa.setDescricao(descricao);
        etapa.setValorTotalSemBdi(BigDecimal.valueOf(valorTotalSemBdi));
        etapa.setValorTotalComBdi(BigDecimal.valueOf(valorTotalComBdi));

        facade.registerEtapa(etapa);
    }

    public void listEtapas() {
        List<Etapa> etapas = facade.listEtapas();
        System.out.printf("%d etapas foram recuperadas.%n", etapas.size());
        etapas.forEach(System.out::println);
    }

    public void removeEtapa() {
        System.out.println("Digite o ID da etapa a ser removida:");

        try {
            Long id = scanner.nextLong();
            facade.deleteEtapa(facade.findEtapaById(id));
        } catch (Exception e) {
            System.out.println("Erro: Etapa não encontrada na base de dados.");
            scanner.nextLine();
        }
    }
}
