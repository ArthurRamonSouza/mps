import java.math.BigDecimal;

import com.ufpb.mps.equipe.grupo5.model.Insumo;

public class InsumoValidator {

    public static void validateInsumo(Insumo insumo) throws Exception {
        if (insumo == null) {
            throw new Exception("Insumo não pode ser nulo.");
        }

        if (insumo.getDescricao() == null || insumo.getDescricao().isEmpty()) {
            throw new Exception("A descrição do insumo é obrigatória.");
        }

        if (insumo.getValorDesonerado() == null) {
            throw new Exception("O valor desonerado do insumo é obrigatório.");
        }

        if (insumo.getValorDesonerado().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O valor desonerado do insumo deve ser maior que zero.");
        }

        if (insumo.getUnidade() == null || insumo.getUnidade().isEmpty()) {
            throw new Exception("A unidade do insumo é obrigatória.");
        }
    }
}
