package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.model.Cotacao;
import com.ufpb.mps.equipe.grupo5.service.CotacaoService;
import com.ufpb.mps.equipe.grupo5.service.Service;

public class CotacaoServiceFactory {
    
    public static Service<Cotacao> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new CotacaoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
