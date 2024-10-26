package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.service.Service;
import com.ufpb.mps.equipe.grupo5.service.OrcamentoDatabaseService;

public class OrcamentoServiceFactory  {
    public static Service<Orcamento> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new OrcamentoDatabaseService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
