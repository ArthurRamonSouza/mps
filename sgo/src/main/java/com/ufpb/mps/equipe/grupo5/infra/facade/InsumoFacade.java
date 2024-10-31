package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;
import com.ufpb.mps.equipe.grupo5.controller.InsumoController;
import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class InsumoFacade {
    private static InsumoFacade instance;
    private final InsumoController insumoController;

    private InsumoFacade() {
        this.insumoController = InsumoController.getInstance();
    }

    public static synchronized InsumoFacade getInstance() {
        if (instance == null) {
            instance = new InsumoFacade();
        }
        return instance;
    }

    public void registerInsumo(Insumo insumo) {
        insumoController.registerInsumoDatabase(insumo);
    }

    public List<Insumo> listInsumos() {
        return insumoController.listInsumos();
    }

    public void updateInsumo(Insumo insumo) {
        insumoController.updateInsumo(insumo);
    }

    public void deleteInsumo(Insumo insumo) {
        insumoController.deleteInsumoDatabase(insumo);
    }

    public Insumo findInsumoById(Long id) {
        return insumoController.findInsumoById(id);
    }
}
