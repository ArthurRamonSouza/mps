import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoValidator {

    // Método para validar o orçamento
    public static void validateOrcamento(Orcamento orcamento) throws IllegalArgumentException {
        if (orcamento == null) {
            throw new IllegalArgumentException("O orçamento não pode ser nulo.");
        }

        if (orcamento.getDescricao() == null || orcamento.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do orçamento não pode ser vazia.");
        }

        if (orcamento.getDataCriacao() == null) {
            throw new IllegalArgumentException("A data de criação do orçamento é obrigatória.");
        }
  }
}