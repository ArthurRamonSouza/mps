package com.ufpb.mps.equipe.grupo5.business.factory;

import com.ufpb.mps.equipe.grupo5.business.data.service.InsumoService;
import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class InsumoServiceFactory {
    
    public static Service<Insumo> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new InsumoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
