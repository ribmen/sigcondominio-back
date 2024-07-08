package br.com.victor.sigcondominio.api.controller;

import br.com.victor.sigcondominio.domain.model.PrestadorServico;
import br.com.victor.sigcondominio.domain.repository.PrestadorServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/prestadorServicos")
public class PrestadorServicoController {

    @Autowired
    private final PrestadorServicoRepository prestadorServicoRepository;

    @GetMapping
    public List<PrestadorServico> listar() {
        return prestadorServicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorServico> findById(@PathVariable Long id) {
        Optional<PrestadorServico> prestadorServicoOptional = prestadorServicoRepository.findById(id);
        if(prestadorServicoOptional.isPresent()) {
            return ResponseEntity.ok(prestadorServicoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PrestadorServico> create(@RequestBody PrestadorServico prestadorServico) {
        PrestadorServico prestadorServicoSalvo = prestadorServicoRepository.save(prestadorServico);
        return ResponseEntity.ok(prestadorServicoSalvo);
    }

}


