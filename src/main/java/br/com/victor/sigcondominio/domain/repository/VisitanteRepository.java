package br.com.victor.sigcondominio.domain.repository;

import br.com.victor.sigcondominio.domain.model.PrestadorServico;
import br.com.victor.sigcondominio.domain.model.Visitante;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitanteRepository extends JpaRepository<Visitante, Long> {
    List<Visitante> findByNome(String nome);

    List<Visitante> findByNomeContaining(String nome);

    Optional<Visitante> findByCpf(String cpf);

    boolean existsByCpf(String visitanteCpf);

    void deleteByCpf(String visitanteCpf);
}
