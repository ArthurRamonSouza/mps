package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.model.Insumo;
import com.ufpb.mps.equipe.grupo5.service.InsumoService;
import com.ufpb.mps.equipe.grupo5.service.Service;

public class InsumoServiceFactory {
    
    public static Service<Insumo> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new InsumoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
