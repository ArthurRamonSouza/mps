package com.ufpb.mps.equipe.grupo5.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.OrcamentoFacade;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoView {

    private final OrcamentoFacade facade;
    private final Scanner scanner;

    public OrcamentoView(Scanner scanner) {
        this.facade = OrcamentoFacade.getInstance(); // Singleton
        this.scanner = scanner;
    }

    public void orcamentoMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar orçamento");
            System.out.println("2. Listar todos os orçamentos");
            System.out.println("3. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerOrcamento();
                case 2 -> listOrcamentos();
                case 3 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void registerOrcamento() {
        System.out.println("Digite a descrição do orçamento:");
        String descricao = scanner.nextLine();

        System.out.println("Digite o município:");
        String municipio = scanner.nextLine();

        System.out.println("Digite o endereço:");
        String endereco = scanner.nextLine();

        System.out.println("Digite a data base (formato: dd/MM/yyyy):");
        String dataBaseStr = scanner.nextLine();
        Date dataBase = parseDate(dataBaseStr); 

        System.out.println("Digite o protocolo:");
        String protocolo = scanner.nextLine();

        System.out.println("Permitir preço zerado? (true/false):");
        boolean permitirPrecoZerado = scanner.nextBoolean();

        System.out.println("Digite o total BDI:");
        Double totalBdi = scanner.nextDouble();

        System.out.println("Digite o valor total sem BDI:");
        Double valorTotalSemBdi = scanner.nextDouble();

        System.out.println("Digite o valor total:");
        Double valorTotal = scanner.nextDouble();

        Orcamento orcamento = new Orcamento();
        orcamento.setDescricao(descricao);
        orcamento.setMunicipio(municipio);
        orcamento.setEndereco(endereco);
        orcamento.setDataBase(dataBase);
        orcamento.setProtocolo(protocolo);
        orcamento.setPermitirPrecoZerado(permitirPrecoZerado);
        orcamento.setTotalBdi(totalBdi);
        orcamento.setValorTotalSemBdi(valorTotalSemBdi);
        orcamento.setValorTotal(valorTotal);
        orcamento.setDataCriacao(new Date());

        facade.registerOrcamento(orcamento);
    }

    public void listOrcamentos() {
        List<Orcamento> orcamentos = facade.listOrcamentos();
        System.out.printf("%d orçamentos foram recuperados.%n", orcamentos.size());
        orcamentos.forEach(System.out::println);
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Usando data atual.");
            return new Date();
        }
    }
}
