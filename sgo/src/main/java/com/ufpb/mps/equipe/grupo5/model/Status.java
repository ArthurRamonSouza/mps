package com.ufpb.mps.equipe.grupo5.model;

public enum Status {
    ABERTO("ABERTO"),
    APROVADO("APROVADO"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    REJEITADO("REJEITADO"),
    FINALIZADO("FINALIZADO");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
