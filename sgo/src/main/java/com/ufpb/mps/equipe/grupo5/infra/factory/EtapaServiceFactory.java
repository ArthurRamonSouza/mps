package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.data.service.EtapaService;
import com.ufpb.mps.equipe.grupo5.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Etapa;

public class EtapaServiceFactory {
    
    public static Service<Etapa> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new EtapaService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
