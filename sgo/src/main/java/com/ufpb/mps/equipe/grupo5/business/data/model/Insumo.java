package com.ufpb.mps.equipe.grupo5.business.data.model;

import com.ufpb.mps.equipe.grupo5.composite.OrcamentoComponent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "insumos")
public class Insumo implements OrcamentoComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "insumo_orcamento",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "orcamento_id"))
    private List<Orcamento> orcamento;

    @ManyToMany
    @JoinTable(name = "insumo_etapa",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "etapa_id"))
    private List<Etapa> etapa;

    @ManyToMany
    @JoinTable(name = "insumo_composicao",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "composicao_id"))
    private List<Composicao> composicoes;

    @Column(nullable = false)
    private String descricao;

    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Tipo tipo;

    @Column(length = 20, nullable = false)
    private String unidade = "und";

    @Column(name = "valor_desonerado", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorDesonerado;

    @Column(name = "valor_nao_desonerado", precision = 12, scale = 2)
    private BigDecimal valorNaoDesonerado;

    @Override
    public double calcularCusto() {
        return valorDesonerado != null ? valorDesonerado.doubleValue() : 0.0;
    }

    @Override
    public String getDescricao() {
        return "Descrição: " + descricao +
               "\nTipo: " + tipo.getDescricao() +
               "\nUnidade: " + unidade +
               "\nValor Desonerado: " + valorDesonerado +
               "\nValor Não Desonerado: " + (valorNaoDesonerado != null ? valorNaoDesonerado : "N/A");
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}

enum Tipo {
    EQUIPAMENTO("Equipamento"),
    EQUIPAMENTO_PERMANENTE("Equipamento Permanente"),
    MAO_DE_OBRA("Mão de Obra"),
    MATERIAL("Material"),
    SERVICOS("Serviços"),
    TAXA("Taxa"),
    ADMINISTRACAO("Administração"),
    ALUGUEL("Aluguel"),
    VERBA("Verba"),
    TRANSPORTE("Transporte"),
    FRANQUIA("Franquia"),
    OUTROS("Outros");

    private final String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}