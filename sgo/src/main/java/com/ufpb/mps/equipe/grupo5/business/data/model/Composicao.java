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
@Table(name = "composicoes")
public class Composicao implements OrcamentoComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "orcamento_composicoes",
               joinColumns = @JoinColumn(name = "composicao_id"),
               inverseJoinColumns = @JoinColumn(name = "orcamento_id"))
    private List<Orcamento> orcamento;

    @ManyToMany
    @JoinTable(name = "base_composicoes",
               joinColumns = @JoinColumn(name = "composicao_id"),
               inverseJoinColumns = @JoinColumn(name = "base_id"))
    private List<BaseOrcamento> bases;

    @ManyToMany
    @JoinTable(name = "etapa_composicoes",
               joinColumns = @JoinColumn(name = "composicao_id"),
               inverseJoinColumns = @JoinColumn(name = "etapa_id"))
    private List<Etapa> etapa;

    @ManyToMany
    @JoinTable(name = "composicoes_relacionadas",
               joinColumns = @JoinColumn(name = "composicao_id"),
               inverseJoinColumns = @JoinColumn(name = "composicao_relacionada_id"))
    private List<Composicao> composicoes;

    @Column(nullable = false)
    private String descricao;

    @Column
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(length = 150)
    private Tipo tipo;

    @Column(name = "is_mao_de_obra", nullable = false)
    private boolean isMaoDeObra;

    @Column(length = 20, nullable = false)
    private String unidade = "und";

    @Column(name = "valor_total", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "valor_total_sem_bdi", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorTotalSemBdi = BigDecimal.ZERO;

    @Column(name = "bdi", precision = 12, scale = 2)
    private BigDecimal bdi = BigDecimal.ZERO;

    @Column(name = "valor_total_bdi", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorTotalBdi = BigDecimal.ZERO;

    @Override
    public double calcularCusto() {
        return valorTotal != null ? valorTotal.doubleValue() : 0.0;
    }

    @Override
    public String getDescricao() {
        return "Descrição: " + descricao + "\n" +
               "Tipo: " + (tipo != null ? tipo.getDescricao() : "N/A") + "\n" +
               "Unidade: " + unidade + "\n" +
               "Valor Total: " + valorTotal + "\n" +
               "Valor Total sem BDI: " + valorTotalSemBdi + "\n" +
               "BDI: " + bdi;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}

enum Tipo {
    ASTU("Assentamento de tubos e peças"),
    CANT("Canteiro de obras"),
    COBE("Cobertura"),
    CHOR("Custos horários de máquinas e equipamentos"),
    DROP("Drenagem/Obras de contenção/Poços de visita e caixas"),
    ESCO("Escoramento"),
    ESQV("Esquadrias/Ferragens/Vidros"),
    FOMA("Fornecimento de materiais e equipamentos"),
    FUES("Fundações e estruturas"),
    IMPE("Impermeabilizações e proteções diversas"),
    INEL("Instalação elétrica/Eletrificação e iluminação externa"),
    INPR("Instalações de produção"),
    INES("Instalações especiais"),
    INHI("Instalações hidros sanitárias"),
    LIPR("Ligações prediais água/esgoto/energia/telefone"),
    MOVT("Movimento de terra"),
    PARE("Paredes/Painéis"),
    PAVI("Pavimentação"),
    PINT("Pinturas"),
    PISO("Pisos"),
    REVE("Revestimento e tratamento de superfícies"),
    SEDI("Serviços diversos"),
    SEEM("Serviços empreitados"),
    SEES("Serviços especiais"),
    SEOP("Serviços operacionais"),
    SERP("Serviços preliminares"),
    SERT("Serviços técnicos"),
    TRAN("Transportes, cargas e descargas"),
    URBA("Urbanização");

    private final String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
