package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.factory.ComposicaoServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.Composicao;
import com.ufpb.mps.equipe.grupo5.service.Service;
import com.ufpb.mps.equipe.grupo5.util.ComposicaoValidator;

public class ComposicaoController {

    private static ComposicaoController instance;
    private Service<Composicao> composicaoService;

    public static synchronized ComposicaoController getInstance() {
        if (instance == null) {
            instance = new ComposicaoController();
        }
        return instance;
    }

    public void registerComposicaoDatabase(Composicao composicao) {
        try {
            ComposicaoValidator.validateComposicao(composicao);

            this.composicaoService = ComposicaoServiceFactory.createRepository("database");
            this.composicaoService.save(composicao);

            System.out.println("Composição registrada com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar composição: " + e.getMessage());
        }
    }

    public List<Composicao> listComposicoes() {
        this.composicaoService = ComposicaoServiceFactory.createRepository("database");
            
        if (this.composicaoService.findAll().isPresent())
            return this.composicaoService.findAll().get();

        return new ArrayList<Composicao>();
    }

    public void updateComposicao(Composicao composicao) {
        try {
            ComposicaoValidator.validateComposicao(composicao);

            this.composicaoService = ComposicaoServiceFactory.createRepository("database");
            this.composicaoService.update(composicao);

            System.out.println("Composição atualizada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar composição: " + e.getMessage());
        }
    }

    public void deleteComposicaoDatabase(Composicao composicao) {
        try {
            this.composicaoService = ComposicaoServiceFactory.createRepository("database");
            this.composicaoService.delete(composicao);

            System.out.println("Composição deletada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar composição: " + e.getMessage());
        }
    }

    public Composicao findComposicaoById(Long id) {
        this.composicaoService = ComposicaoServiceFactory.createRepository("database");
        return this.composicaoService.findBy(id).orElse(null);
    }
}
