package br.com.victor.sigcondominio.domain.service;

import br.com.victor.sigcondominio.domain.exception.NegocioException;
import br.com.victor.sigcondominio.domain.model.Visitante;
import br.com.victor.sigcondominio.domain.repository.VisitanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class CadastroVisitanteService {

    private final VisitanteRepository visitanteRepository;

    public Visitante buscar(Long visitanteId) {
        return visitanteRepository.findById(visitanteId)
                .orElseThrow(() -> new NegocioException("Visitante não encontrado"));
    }

    @Transactional
    public Visitante salvar(Visitante visitante) {
        boolean cpfEmUso = visitanteRepository.findByCpf(visitante.getCpf())
                .filter(c-> !c.equals(visitante))
                .isPresent();

        if (cpfEmUso) {
            throw new NegocioException("Já existe um visitante cadastrado com este CPF.");
        }

        return visitanteRepository.save(visitante);
    }

    @Transactional
    public void excluir(String visitanteCpf) {
        visitanteRepository.deleteByCpf(visitanteCpf);
    }
}
