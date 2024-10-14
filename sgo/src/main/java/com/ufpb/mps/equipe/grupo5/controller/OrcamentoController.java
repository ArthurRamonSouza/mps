package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.service.OrcamentoDatabaseService;
import com.ufpb.mps.equipe.grupo5.util.OrcamentoValidator;

public class OrcamentoController {

    private static OrcamentoController instance;
    private final OrcamentoDatabaseService orcamentoDatabaseService;

    private OrcamentoController() {
        this.orcamentoDatabaseService = new OrcamentoDatabaseService();
    }

    public static synchronized OrcamentoController getInstance() {
        if (instance == null) {
            instance = new OrcamentoController();
        }
        return instance;
    }

    public void registerOrcamentoDatabase(Orcamento orcamento) {
        try {
            OrcamentoValidator.validateOrcamento(orcamento);
            orcamentoDatabaseService.save(orcamento);
            System.out.println("Orçamento registrado com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar orçamento: " + e.getMessage());
        }
    }

    public List<Orcamento> listOrcamentos() {
        if (orcamentoDatabaseService.findAll().isPresent())
            return orcamentoDatabaseService.findAll().get();

        return new ArrayList<Orcamento>();
    }

    public void updateOrcamento(Orcamento orcamento) {
        try {
            OrcamentoValidator.validateOrcamento(orcamento);
            orcamentoDatabaseService.update(orcamento);
            System.out.println("Orçamento atualizado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar orçamento: " + e.getMessage());
        }
    }

    public void deleteOrcamentoDatabase(Orcamento orcamento) {
        try {
            orcamentoDatabaseService.delete(orcamento);
            System.out.println("Orçamento deletado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar orçamento: " + e.getMessage());
        }
    }

    public Orcamento findOrcamentoById(Long id) {
        return orcamentoDatabaseService.findById(id).orElse(null);
    }
}