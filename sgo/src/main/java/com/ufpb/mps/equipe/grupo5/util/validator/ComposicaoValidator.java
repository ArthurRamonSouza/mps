package com.ufpb.mps.equipe.grupo5.util.validator;

import java.math.BigDecimal;

import com.ufpb.mps.equipe.grupo5.business.data.model.Composicao;

public class ComposicaoValidator {

    public static void validateComposicao(Composicao composicao) throws Exception {
        if (composicao == null) {
            throw new Exception("Composição não pode ser nula.");
        }

        if (composicao.getDescricao() == null || composicao.getDescricao().isEmpty()) {
            throw new Exception("A descrição da composição é obrigatória.");
        }

        if (composicao.getValorTotal() == null) {
            throw new Exception("O valor total da composição é obrigatório.");
        }

        if (composicao.getValorTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O valor total da composição deve ser maior que zero.");
        }
    }
}
