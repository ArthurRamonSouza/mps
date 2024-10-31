package com.ufpb.mps.equipe.grupo5.business.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

import com.ufpb.mps.equipe.grupo5.composite.OrcamentoComponent;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cotacao")
public class Cotacao implements OrcamentoComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cotacionista_id", referencedColumnName = "id", nullable = false)
    private User cotacionista;

    @Column(name = "item", length = 100, nullable = false)
    private String item;

    @Column(name = "unidade", length = 50, nullable = false)
    private String unidade = "und";

    @Column(name = "quantidade", precision = 10, scale = 2, nullable = false)
    private BigDecimal quantidade;

    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @Column(name = "menor_preco", precision = 10, scale = 2)
    private BigDecimal menorPreco;

    @Column(name = "preco_medio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoMedio;

    @Column(name = "maior_preco", precision = 10, scale = 2)
    private BigDecimal maiorPreco;

    @Column(name = "emissao", nullable = false)
    private Date emissao;

    @Override
    public String getDescricao () {
        String description = "Item: " + item + ", Unidade: " + unidade + ", Quantidade: " + quantidade;
        description.concat("\nDescrição: " + descricao + ", Menor Preço: " + menorPreco);
        description.concat("\nPreço Médio: " + precoMedio + ", Maior Preço: " + maiorPreco);
        return description;
    }

    @Override
    public double calcularCusto() {
        if (precoMedio != null && quantidade != null) {
            return precoMedio.multiply(quantidade).doubleValue();
        } else {
            throw new IllegalStateException("Preço médio e quantidade devem estar definidos para calcular o custo.");
        }
    }
}
