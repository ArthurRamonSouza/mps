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
@Table(name = "etapas")
public class Etapa implements OrcamentoComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "orcamento_id", referencedColumnName = "id", nullable = false)
    private Orcamento orcamento;

    @ManyToMany
    @JoinTable(name = "etapa_subetapas",
               joinColumns = @JoinColumn(name = "etapa_id"),
               inverseJoinColumns = @JoinColumn(name = "subetapa_id"))
    private List<Etapa> subetapas;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "valor_total_com_bdi", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorTotalComBdi = BigDecimal.ZERO;

    @Column(name = "valor_total_sem_bdi", precision = 12, scale = 2, nullable = false)
    private BigDecimal valorTotalSemBdi = BigDecimal.ZERO;

    @Override
    public double calcularCusto() {
        return valorTotalComBdi != null ? valorTotalComBdi.doubleValue() : 0.0;
    }

    @Override
    public String getDescricao() {
        return "Descrição: " + descricao + "\n" +
               "Valor Total com BDI: " + valorTotalComBdi + "\n" +
               "Valor Total sem BDI: " + valorTotalSemBdi;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
}
