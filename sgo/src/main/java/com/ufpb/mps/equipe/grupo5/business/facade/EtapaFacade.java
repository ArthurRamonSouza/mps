package com.ufpb.mps.equipe.grupo5.business.facade;

import java.util.List;

import com.ufpb.mps.equipe.grupo5.business.controller.EtapaController;
import com.ufpb.mps.equipe.grupo5.model.Etapa;

public class EtapaFacade {
    private static EtapaFacade instance;
    private final EtapaController etapaController;

    private EtapaFacade() {
        this.etapaController = EtapaController.getInstance();
    }

    public static synchronized EtapaFacade getInstance() {
        if (instance == null) {
            instance = new EtapaFacade();
        }
        return instance;
    }

    public void registerEtapa(Etapa etapa) {
        etapaController.registerEtapaDatabase(etapa);
    }

    public List<Etapa> listEtapas() {
        return etapaController.listEtapas();
    }

    public void updateEtapa(Etapa etapa) {
        etapaController.updateEtapa(etapa);
    }

    public void deleteEtapa(Etapa etapa) {
        etapaController.deleteEtapaDatabase(etapa);
    }

    public Etapa findEtapaById(Long id) {
        return etapaController.findEtapaById(id);
    }
}
