package com.ufpb.mps.equipe.grupo5.business.factory;

import com.ufpb.mps.equipe.grupo5.business.data.service.ComposicaoService;
import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Composicao;

public class ComposicaoServiceFactory {
    
    public static Service<Composicao> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new ComposicaoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
