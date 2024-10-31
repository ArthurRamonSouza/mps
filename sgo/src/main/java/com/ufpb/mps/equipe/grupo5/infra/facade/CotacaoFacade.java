package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;
import com.ufpb.mps.equipe.grupo5.controller.CotacaoController;
import com.ufpb.mps.equipe.grupo5.model.Cotacao;

public class CotacaoFacade {
    private static CotacaoFacade instance;
    private final CotacaoController cotacaoController;

    private CotacaoFacade() {
        this.cotacaoController = CotacaoController.getInstance();
    }

    public static synchronized CotacaoFacade getInstance() {
        if (instance == null) {
            instance = new CotacaoFacade();
        }
        return instance;
    }

    public void registerCotacao(Cotacao cotacao) {
        cotacaoController.registerCotacaoDatabase(cotacao);
    }

    public List<Cotacao> listCotacoes() {
        return cotacaoController.listCotacoes();
    }

    public void updateCotacao(Cotacao cotacao) {
        cotacaoController.updateCotacao(cotacao);
    }

    public void deleteCotacao(Cotacao cotacao) {
        cotacaoController.deleteCotacaoDatabase(cotacao);
    }

    public Cotacao findCotacaoById(Long id) {
        return cotacaoController.findCotacaoById(id);
    }
}
