import java.math.BigDecimal;

import com.ufpb.mps.equipe.grupo5.data.model.Cotacao;

public class CotacaoValidator {

    public static void validateCotacao(Cotacao cotacao) throws Exception {
        if (cotacao == null) {
            throw new Exception("Cotação não pode ser nula.");
        }

        if (cotacao.getItem() == null || cotacao.getItem().isEmpty()) {
            throw new Exception("O item da cotação é obrigatório.");
        }

        if (cotacao.getPrecoMedio() == null) {
            throw new Exception("O preço médio da cotação é obrigatório.");
        }

        if (cotacao.getPrecoMedio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O preço médio deve ser maior que zero.");
        }

        if (cotacao.getQuantidade() == null || cotacao.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("A quantidade deve ser maior que zero.");
        }
    }
}
