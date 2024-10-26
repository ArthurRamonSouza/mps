package com.ufpb.mps.equipe.grupo5.model;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import com.ufpb.mps.equipe.grupo5.memento.OrcamentoMemento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor()
@NoArgsConstructor()
@Getter @Setter
@Entity
@Table(name="orcamentos")
public class Orcamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "orcamentista_id")
    private User orcamentista;

    @NotNull
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @Size(max = 50)
    private String municipio;

    @Size(max = 200)
    private String endereco;

    @NotNull
    @Column(name = "data_base")
    private Date dataBase;

    @Size(max = 100)
    private String protocolo;

    @Column(name = "permitir_preco_zerado")
    private boolean permitirPrecoZerado;

    @Column(name = "total_bdi")
    private double totalBdi = 0.0;

    @Column(name = "valor_total_sem_bdi")
    private double valorTotalSemBdi = 0.0;

    @Column(name = "valor_total")
    private double valorTotal = 0.0;

    @Column(name = "encargos_sociais_desonerados")
    private boolean encargosSociaisDesonerados;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ABERTO;

    @Column(name = "is_licitacao")
    private boolean isLicitacao = false;

    @Column(name = "bdi")
    private double bdi = 0.0;

    @Column(name = "etapas")
    @JoinColumn(name = "etapa_id")
    private List<Etapa> etapas;

    @Column(name = "insumos")
    @JoinColumn(name = "insumo_id")
    private List<Insumo> insumos;

    @Column(name = "cotacoes")
    @JoinColumn(name = "cotacao_id")
    private List<Cotacao> cotacoes;

    @Transient
    private Stack<OrcamentoMemento> mementoHistory = new Stack<>();

    @Override
    public String toString() {
        return "Orcamento {" + 
                "\n  ID: " + id + 
                "\n  Orcamentista: " + (orcamentista != null ? orcamentista.getName() : "Não definido") + 
                "\n  Data de Criação: " + dataCriacao + 
                "\n  Descrição: " + descricao + 
                "\n  Município: " + (municipio != null ? municipio : "Não especificado") + 
                "\n  Endereço: " + (endereco != null ? endereco : "Não especificado") + 
                "\n  Data Base: " + dataBase + 
                "\n  Protocolo: " + (protocolo != null ? protocolo : "Não especificado") + 
                "\n  Permitir Preço Zerado: " + (permitirPrecoZerado ? "Sim" : "Não") + 
                "\n  Total com BDI: R$ " + String.format("%.2f", totalBdi) + 
                "\n  Valor Total sem BDI: R$ " + String.format("%.2f", valorTotalSemBdi) + 
                "\n  Valor Total: R$ " + String.format("%.2f", valorTotal) + 
                "\n  Encargos Sociais Desonerados: " + (encargosSociaisDesonerados ? "Sim" : "Não") + 
                "\n  Status: " + status + 
                "\n  É Licitação: " + (isLicitacao ? "Sim" : "Não") + 
                "\n  BDI: " + String.format("%.2f", bdi) + 
                "\n}";
    }

    public void addSnapshot() {
        this.mementoHistory.push(new OrcamentoMemento(this));
    }

    public void restore() {
        if (this.mementoHistory.isEmpty()) return;
    
        OrcamentoMemento memento = this.mementoHistory.pop();
        
        this.id = memento.getId();
        this.orcamentista = memento.getOrcamentista();
        this.dataCriacao = memento.getDataCriacao();
        this.descricao = memento.getDescricao();
        this.municipio = memento.getMunicipio();
        this.endereco = memento.getEndereco();
        this.dataBase = memento.getDataBase();
        this.protocolo = memento.getProtocolo();
        this.permitirPrecoZerado = memento.isPermitirPrecoZerado();
        this.totalBdi = memento.getTotalBdi();
        this.valorTotalSemBdi = memento.getValorTotalSemBdi();
        this.valorTotal = memento.getValorTotal();
        this.encargosSociaisDesonerados = memento.isEncargosSociaisDesonerados();
        this.status = memento.getStatus();
        this.isLicitacao = memento.isLicitacao();
        this.bdi = memento.getBdi();
    }   
}