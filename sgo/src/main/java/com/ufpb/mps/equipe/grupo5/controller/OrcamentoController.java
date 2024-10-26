package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.factory.OrcamentoServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.service.Service;
import com.ufpb.mps.equipe.grupo5.util.OrcamentoValidator;

public class OrcamentoController {

    private static OrcamentoController instance;
    private Service<Orcamento> orcamentoService;

    public static synchronized OrcamentoController getInstance() {
        if (instance == null) {
            instance = new OrcamentoController();
        }
        return instance;
    }

    public void registerOrcamentoDatabase(Orcamento orcamento) {
        try {
            OrcamentoValidator.validateOrcamento(orcamento);

            this.orcamentoService = OrcamentoServiceFactory.createRepository("database");
            this.orcamentoService.save(orcamento);

            System.out.println("Orçamento registrado com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar orçamento: " + e.getMessage());
        }
    }

    public List<Orcamento> listOrcamentos() {
        this.orcamentoService = OrcamentoServiceFactory.createRepository("database");
            
        if (this.orcamentoService.findAll().isPresent())
            return this.orcamentoService.findAll().get();

        return new ArrayList<Orcamento>();
    }

    public void updateOrcamento(Orcamento orcamento) {
        try {
            OrcamentoValidator.validateOrcamento(orcamento);

            this.orcamentoService = OrcamentoServiceFactory.createRepository("database");
            this.orcamentoService.update(orcamento);

            System.out.println("Orçamento atualizado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar orçamento: " + e.getMessage());
        }
    }

    public void deleteOrcamentoDatabase(Orcamento orcamento) {
        try {
            this.orcamentoService = OrcamentoServiceFactory.createRepository("database");
            this.orcamentoService.delete(orcamento);

            System.out.println("Orçamento deletado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar orçamento: " + e.getMessage());
        }
    }

    public Orcamento findOrcamentoById(Long id) {
        this.orcamentoService = OrcamentoServiceFactory.createRepository("database");
        return this.orcamentoService.findBy(id).orElse(null);
    }
}