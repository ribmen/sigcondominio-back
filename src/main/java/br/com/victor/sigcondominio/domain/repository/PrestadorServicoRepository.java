package br.com.victor.sigcondominio.domain.repository;

import br.com.victor.sigcondominio.domain.model.PrestadorServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServico, Long> {

    List<PrestadorServico> findByNome(String nome);

    List<PrestadorServico> findByNomeContaining(String nome);

    Optional<PrestadorServico> findByCpf(String cpf);
}

