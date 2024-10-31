package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;
import com.ufpb.mps.equipe.grupo5.controller.ComposicaoController;
import com.ufpb.mps.equipe.grupo5.model.Composicao;

public class ComposicaoFacade {
    private static ComposicaoFacade instance;
    private final ComposicaoController composicaoController;

    private ComposicaoFacade() {
        this.composicaoController = ComposicaoController.getInstance();
    }

    public static synchronized ComposicaoFacade getInstance() {
        if (instance == null) {
            instance = new ComposicaoFacade();
        }
        return instance;
    }

    public void registerComposicao(Composicao composicao) {
        composicaoController.registerComposicaoDatabase(composicao);
    }

    public List<Composicao> listComposicoes() {
        return composicaoController.listComposicoes();
    }

    public void updateComposicao(Composicao composicao) {
        composicaoController.updateComposicao(composicao);
    }

    public void deleteComposicao(Composicao composicao) {
        composicaoController.deleteComposicaoDatabase(composicao);
    }

    public Composicao findComposicaoById(Long id) {
        return composicaoController.findComposicaoById(id);
    }
}
