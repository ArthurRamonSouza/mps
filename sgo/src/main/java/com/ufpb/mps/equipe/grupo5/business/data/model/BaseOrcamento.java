package com.ufpb.mps.equipe.grupo5.business.data.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="bases_orcamento")
public class BaseOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "base_orcamento_orcamento",
        joinColumns = @JoinColumn(name = "base_orcamento_id"),
        inverseJoinColumns = @JoinColumn(name = "orcamento_id")
    )
    private Set<Orcamento> orcamento;

    @Enumerated(EnumType.STRING)
    private Base base;

    @NotNull
    @Column(nullable = false)
    private Date data;

    public enum Base {
        SINAPI("SINAPI"),
        SBC("SBC"),
        SICRO_3("SICRO 3"),
        ORSE("ORSE"),
        SEINFRA("SEINFRA");

        private final String value;

        Base(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}