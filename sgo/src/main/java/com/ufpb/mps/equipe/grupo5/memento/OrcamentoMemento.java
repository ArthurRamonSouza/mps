package com.ufpb.mps.equipe.grupo5.memento;

import java.util.Date;

import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.model.Status;

public class OrcamentoMemento implements Memento<Orcamento> {
    private final Long id;
    private final User orcamentista;
    private final Date dataCriacao;
    private final String descricao;
    private final String municipio;
    private final String endereco;
    private final Date dataBase;
    private final String protocolo;
    private final boolean permitirPrecoZerado;
    private final double totalBdi;
    private final double valorTotalSemBdi;
    private final double valorTotal;
    private final boolean encargosSociaisDesonerados;
    private final Status status;
    private final boolean isLicitacao;
    private final float bdi;

    public OrcamentoMemento(Orcamento orcamento) {
        this.id = orcamento.getId();
        this.orcamentista = orcamento.getOrcamentista();
        this.dataCriacao = orcamento.getDataCriacao();
        this.descricao = orcamento.getDescricao();
        this.municipio = orcamento.getMunicipio();
        this.endereco = orcamento.getEndereco();
        this.dataBase = orcamento.getDataBase();
        this.protocolo = orcamento.getProtocolo();
        this.permitirPrecoZerado = orcamento.isPermitirPrecoZerado();
        this.totalBdi = orcamento.getTotalBdi();
        this.valorTotalSemBdi = orcamento.getValorTotalSemBdi();
        this.valorTotal = orcamento.getValorTotal();
        this.encargosSociaisDesonerados = orcamento.isEncargosSociaisDesonerados();
        this.status = orcamento.getStatus();
        this.isLicitacao = orcamento.isLicitacao();
        this.bdi = orcamento.getBdi();
    }

    public Long getId() {
        return id;
    }

    public User getOrcamentista() {
        return orcamentista;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getEndereco() {
        return endereco;
    }

    public Date getDataBase() {
        return dataBase;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public boolean isPermitirPrecoZerado() {
        return permitirPrecoZerado;
    }

    public double getTotalBdi() {
        return totalBdi;
    }

    public double getValorTotalSemBdi() {
        return valorTotalSemBdi;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean isEncargosSociaisDesonerados() {
        return encargosSociaisDesonerados;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isLicitacao() {
        return isLicitacao;
    }

    public float getBdi() {
        return bdi;
    }

    @Override
    public Orcamento getState() {
        Orcamento orcamento = new Orcamento();
        orcamento.setId(this.id);
        orcamento.setOrcamentista(this.orcamentista);
        orcamento.setDataCriacao(this.dataCriacao);
        orcamento.setDescricao(this.descricao);
        orcamento.setMunicipio(this.municipio);
        orcamento.setEndereco(this.endereco);
        orcamento.setDataBase(this.dataBase);
        orcamento.setProtocolo(this.protocolo);
        orcamento.setPermitirPrecoZerado(this.permitirPrecoZerado);
        orcamento.setTotalBdi(this.totalBdi);
        orcamento.setValorTotalSemBdi(this.valorTotalSemBdi);
        orcamento.setValorTotal(this.valorTotal);
        orcamento.setEncargosSociaisDesonerados(this.encargosSociaisDesonerados);
        orcamento.setStatus(this.status);
        orcamento.setLicitacao(this.isLicitacao);
        orcamento.setBdi(this.bdi);
        return orcamento;
    }

}
