package com.ufpb.mps.equipe.grupo5.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.ufpb.mps.equipe.grupo5.facade.OrcamentoFacade;
import com.ufpb.mps.equipe.grupo5.facade.ComposicaoFacade;
import com.ufpb.mps.equipe.grupo5.facade.EtapaFacade;
import com.ufpb.mps.equipe.grupo5.facade.InsumoFacade;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.model.Composicao;
import com.ufpb.mps.equipe.grupo5.model.Etapa;
import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class OrcamentoView {

    private final OrcamentoFacade facade;
    private final Scanner scanner;
    private final ComposicaoFacade composicaoFacade;
    private final EtapaFacade etapaFacade;
    private final InsumoFacade insumoFacade;

    public OrcamentoView(Scanner scanner) {
        this.facade = OrcamentoFacade.getInstance(); // Singleton
        this.composicaoFacade = ComposicaoFacade.getInstance();
        this.etapaFacade = EtapaFacade.getInstance();
        this.insumoFacade = InsumoFacade.getInstance();
        this.scanner = scanner;
    }

    
    public void orcamentoMenu() {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar orçamento");
            System.out.println("2. Listar todos os orçamentos");
            System.out.println("3. Deletar orçamento");
            System.out.println("4. Desfazer último comando");
            System.out.println("5. Sair");

            int in = scanner.nextInt();
            scanner.nextLine();

            switch (in) {
                case 1 -> registerOrcamento();
                case 2 -> listOrcamentos();
                case 3 -> removeOrcamento();
                case 4 -> undo();
                case 5 -> {
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
        double totalBdi = scanner.nextDouble();

        System.out.println("Digite o valor total sem BDI:");
        double valorTotalSemBdi = scanner.nextDouble();

        System.out.println("Digite o valor total:");
        double valorTotal = scanner.nextDouble();

        // Adicionar composições, etapas ou insumos
        List<Composicao> composicoes = addComposicoes();
        List<Etapa> etapas = addEtapas();
        List<Insumo> insumos = addInsumos();

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

        // Adicionar composições, etapas e insumos ao orçamento
        orcamento.setComposicoes(composicoes);
        orcamento.setEtapas(etapas);
        orcamento.setInsumos(insumos);

        facade.registerOrcamento(orcamento);
    }

    private List<Composicao> addComposicoes() {
        List<Composicao> composicoes = new ArrayList<>();
        System.out.println("Deseja adicionar composições ao orçamento? (true/false)");
        boolean adicionar = scanner.nextBoolean();
        scanner.nextLine();
        while (adicionar) {
            System.out.println("Digite o ID da composição para adicionar ao orçamento:");
            Long id = scanner.nextLong();
            scanner.nextLine();
            Composicao composicao = composicaoFacade.findComposicaoById(id);
            if (composicao != null) {
                composicoes.add(composicao);
                System.out.println("Composição adicionada ao orçamento.");
            } else {
                System.out.println("Composição não encontrada.");
            }
            System.out.println("Deseja adicionar mais composições? (true/false)");
            adicionar = scanner.nextBoolean();
            scanner.nextLine();
        }
        return composicoes;
    }

    private List<Etapa> addEtapas() {
        List<Etapa> etapas = new ArrayList<>();
        System.out.println("Deseja adicionar etapas ao orçamento? (true/false)");
        boolean adicionar = scanner.nextBoolean();
        scanner.nextLine();
        while (adicionar) {
            System.out.println("Digite o ID da etapa para adicionar ao orçamento:");
            Long id = scanner.nextLong();
            scanner.nextLine();
            Etapa etapa = etapaFacade.findEtapaById(id);
            if (etapa != null) {
                etapas.add(etapa);
                System.out.println("Etapa adicionada ao orçamento.");
            } else {
                System.out.println("Etapa não encontrada.");
            }
            System.out.println("Deseja adicionar mais etapas? (true/false)");
            adicionar = scanner.nextBoolean();
            scanner.nextLine();
        }
        return etapas;
    }

    private List<Insumo> addInsumos() {
        List<Insumo> insumos = new ArrayList<>();
        System.out.println("Deseja adicionar insumos ao orçamento? (true/false)");
        boolean adicionar = scanner.nextBoolean();
        scanner.nextLine();
        while (adicionar) {
            System.out.println("Digite o ID do insumo para adicionar ao orçamento:");
            Long id = scanner.nextLong();
            scanner.nextLine();
            Insumo insumo = insumoFacade.findInsumoById(id);
            if (insumo != null) {
                insumos.add(insumo);
                System.out.println("Insumo adicionado ao orçamento.");
            } else {
                System.out.println("Insumo não encontrado.");
            }
            System.out.println("Deseja adicionar mais insumos? (true/false)");
            adicionar = scanner.nextBoolean();
            scanner.nextLine();
        }
        return insumos;
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

    public void removeOrcamento() {
        System.out.println("Digite o ID do orçamento a ser removido:");
        
        try {
            Long id = scanner.nextLong();
            facade.deleteOrcamento(facade.findOrcamentoById(id));
        } catch (Exception e) {
            System.out.println("Erro: Orçamento não encontrado na base de dados.");
            scanner.nextLine();
        }
    }

    public void undo() {
        this.facade.undo();
    }
}
