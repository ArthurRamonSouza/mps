package com.ufpb.mps.equipe.grupo5.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.InsumoFacade;
import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class InsumoView {

    private final InsumoFacade facade;
    private final Scanner scanner;

    public InsumoView(Scanner scanner) {
        this.facade = InsumoFacade.getInstance();
        this.scanner = scanner;
    }

    public void insumoMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar insumo");
            System.out.println("2. Listar todos os insumos");
            System.out.println("3. Deletar insumo");
            System.out.println("4. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerInsumo();
                case 2 -> listInsumos();
                case 3 -> removeInsumo();
                case 4 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registerInsumo() {
        System.out.println("Digite a descrição do insumo:");
        String descricao = scanner.nextLine();

        System.out.println("Digite a unidade do insumo:");
        String unidade = scanner.nextLine();

        System.out.println("Digite o valor desonerado:");
        double valorDesonerado = scanner.nextDouble();

        Insumo insumo = new Insumo();
        insumo.setDescricao(descricao);
        insumo.setUnidade(unidade);
        insumo.setValorDesonerado(BigDecimal.valueOf(valorDesonerado));

        facade.registerInsumo(insumo);
    }

    public void listInsumos() {
        List<Insumo> insumos = facade.listInsumos();
        System.out.printf("%d insumos foram recuperados.%n", insumos.size());
        insumos.forEach(System.out::println);
    }

    public void removeInsumo() {
        System.out.println("Digite o ID do insumo a ser removido:");

        try {
            Long id = scanner.nextLong();
            facade.deleteInsumo(facade.findInsumoById(id));
        } catch (Exception e) {
            System.out.println("Erro: Insumo não encontrado na base de dados.");
            scanner.nextLine();
        }
    }
}
