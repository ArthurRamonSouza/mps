package com.ufpb.mps.equipe.grupo5.builder;

import java.util.Date;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.model.Cotacao;
import com.ufpb.mps.equipe.grupo5.model.Etapa;
import com.ufpb.mps.equipe.grupo5.model.Insumo;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrcamentoBuilder {
    private String descricao;
    private List<Etapa> etapas;
    private List<Insumo> insumos;
    private List<Cotacao> cotacoes;
    private Date dataCriacao;

    public OrcamentoBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Orcamento build() {
        return new Orcamento(this);
    }
}