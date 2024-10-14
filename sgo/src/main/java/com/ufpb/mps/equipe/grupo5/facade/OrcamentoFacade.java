package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;

import com.ufpb.mps.equipe.grupo5.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoFacade {
    private static OrcamentoFacade instance;
    private final OrcamentoController orcamentoController;

    private OrcamentoFacade() {
        this.orcamentoController = OrcamentoController.getInstance();
    }

    public static synchronized OrcamentoFacade getInstance(){
        if(instance == null){
            instance = new OrcamentoFacade();
        }
        return instance;
    }


    public void registerOrcamento(Orcamento orcamento) {
        orcamentoController.registerOrcamentoDatabase(orcamento);
    }

    public List<Orcamento> listOrcamentos() {
        return orcamentoController.listOrcamentos();
    }

    public void updateOrcamento(Orcamento orcamento) {
        orcamentoController.updateOrcamento(orcamento);
    }

    public void deleteOrcamento(Orcamento orcamento) {
        orcamentoController.deleteOrcamentoDatabase(orcamento);
    }

    public Orcamento findOrcamentoById(Long id) {
        return orcamentoController.findOrcamentoById(id);
    }
    
}
