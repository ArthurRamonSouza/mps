package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.data.service.InsumoService;
import com.ufpb.mps.equipe.grupo5.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class InsumoServiceFactory {
    
    public static Service<Insumo> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new InsumoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
