package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.factory.CotacaoServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.Cotacao;
import com.ufpb.mps.equipe.grupo5.service.Service;
import com.ufpb.mps.equipe.grupo5.util.CotacaoValidator;

public class CotacaoController {

    private static CotacaoController instance;
    private Service<Cotacao> cotacaoService;

    public static synchronized CotacaoController getInstance() {
        if (instance == null) {
            instance = new CotacaoController();
        }
        return instance;
    }

    public void registerCotacaoDatabase(Cotacao cotacao) {
        try {
            CotacaoValidator.validateCotacao(cotacao);

            this.cotacaoService = CotacaoServiceFactory.createRepository("database");
            this.cotacaoService.save(cotacao);

            System.out.println("Cotação registrada com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar cotação: " + e.getMessage());
        }
    }

    public List<Cotacao> listCotacoes() {
        this.cotacaoService = CotacaoServiceFactory.createRepository("database");
            
        if (this.cotacaoService.findAll().isPresent())
            return this.cotacaoService.findAll().get();

        return new ArrayList<Cotacao>();
    }

    public void updateCotacao(Cotacao cotacao) {
        try {
            CotacaoValidator.validateCotacao(cotacao);

            this.cotacaoService = CotacaoServiceFactory.createRepository("database");
            this.cotacaoService.update(cotacao);

            System.out.println("Cotação atualizada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar cotação: " + e.getMessage());
        }
    }

    public void deleteCotacaoDatabase(Cotacao cotacao) {
        try {
            this.cotacaoService = CotacaoServiceFactory.createRepository("database");
            this.cotacaoService.delete(cotacao);

            System.out.println("Cotação deletada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar cotação: " + e.getMessage());
        }
    }

    public Cotacao findCotacaoById(Long id) {
        this.cotacaoService = CotacaoServiceFactory.createRepository("database");
        return this.cotacaoService.findBy(id).orElse(null);
    }
}
