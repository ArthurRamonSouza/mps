package com.ufpb.mps.equipe.grupo5.model;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "orcamentista_id", nullable = true)
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

    @Column(name = "total_bdi", precision = 12, scale = 2)
    private double totalBdi = 0.0;

    @Column(name = "valor_total_sem_bdi", precision = 12, scale = 2)
    private double valorTotalSemBdi = 0.0;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private double valorTotal = 0.0;

    @Column(name = "encargos_sociais_desonerados")
    private boolean encargosSociaisDesonerados;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ABERTO;

    @Column(name = "is_licitacao")
    private boolean isLicitacao = false;

    private float bdi = 0.0f;

    @Override
    public String toString() {
        return descricao;
    }

   
}
