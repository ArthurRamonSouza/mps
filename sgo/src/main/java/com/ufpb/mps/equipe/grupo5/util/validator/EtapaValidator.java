import java.math.BigDecimal;

import com.ufpb.mps.equipe.grupo5.model.Etapa;

public class EtapaValidator {

    public static void validateEtapa(Etapa etapa) throws Exception {
        if (etapa == null) {
            throw new Exception("Etapa não pode ser nula.");
        }

        if (etapa.getDescricao() == null || etapa.getDescricao().isEmpty()) {
            throw new Exception("A descrição da etapa é obrigatória.");
        }

        if (etapa.getValorTotalComBdi() == null || etapa.getValorTotalComBdi().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O valor total com BDI da etapa deve ser maior que zero.");
        }

        if (etapa.getValorTotalSemBdi() == null || etapa.getValorTotalSemBdi().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O valor total sem BDI da etapa deve ser maior que zero.");
        }
    }
}
