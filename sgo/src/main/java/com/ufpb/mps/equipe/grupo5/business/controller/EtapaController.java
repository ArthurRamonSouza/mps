package com.ufpb.mps.equipe.grupo5.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.factory.EtapaServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.Etapa;
import com.ufpb.mps.equipe.grupo5.util.template.EtapaValidator;

public class EtapaController {

    private static EtapaController instance;
    private Service<Etapa> etapaService;

    public static synchronized EtapaController getInstance() {
        if (instance == null) {
            instance = new EtapaController();
        }
        return instance;
    }

    public void registerEtapaDatabase(Etapa etapa) {
        try {
            EtapaValidator.validateEtapa(etapa);

            this.etapaService = EtapaServiceFactory.createRepository("database");
            this.etapaService.save(etapa);

            System.out.println("Etapa registrada com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar etapa: " + e.getMessage());
        }
    }

    public List<Etapa> listEtapas() {
        this.etapaService = EtapaServiceFactory.createRepository("database");
            
        if (this.etapaService.findAll().isPresent())
            return this.etapaService.findAll().get();

        return new ArrayList<Etapa>();
    }

    public void updateEtapa(Etapa etapa) {
        try {
            EtapaValidator.validateEtapa(etapa);

            this.etapaService = EtapaServiceFactory.createRepository("database");
            this.etapaService.update(etapa);

            System.out.println("Etapa atualizada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar etapa: " + e.getMessage());
        }
    }

    public void deleteEtapaDatabase(Etapa etapa) {
        try {
            this.etapaService = EtapaServiceFactory.createRepository("database");
            this.etapaService.delete(etapa);

            System.out.println("Etapa deletada com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar etapa: " + e.getMessage());
        }
    }

    public Etapa findEtapaById(Long id) {
        this.etapaService = EtapaServiceFactory.createRepository("database");
        return this.etapaService.findBy(id).orElse(null);
    }
}
