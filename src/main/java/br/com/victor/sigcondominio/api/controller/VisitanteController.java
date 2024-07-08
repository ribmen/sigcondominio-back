package br.com.victor.sigcondominio.api.controller;

import br.com.victor.sigcondominio.domain.exception.NegocioException;
import br.com.victor.sigcondominio.domain.model.PrestadorServico;
import br.com.victor.sigcondominio.domain.model.Visitante;
import br.com.victor.sigcondominio.domain.repository.PrestadorServicoRepository;
import br.com.victor.sigcondominio.domain.repository.VisitanteRepository;
import br.com.victor.sigcondominio.domain.service.CadastroVisitanteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/visitantes")
public class VisitanteController {

    @Autowired
    private final VisitanteRepository visitanteRepository;

    private final CadastroVisitanteService cadastroVisitanteService;

    @GetMapping
    public List<Visitante> listar() {
        return visitanteRepository.findAll();
    }

    @GetMapping("/{visitanteId}")
    public ResponseEntity<Visitante> buscar(@PathVariable Long visitanteId) {
        Optional<Visitante> visitanteOptional = visitanteRepository.findById(visitanteId);
        if(visitanteOptional.isPresent()) {
            return ResponseEntity.ok(visitanteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cpf/{visitanteCpf}")
    public ResponseEntity<Visitante> buscarCpf(@PathVariable String visitanteCpf) {
        Optional<Visitante> visitanteOptional = visitanteRepository.findByCpf(visitanteCpf);
        if(visitanteOptional.isPresent()) {
            return ResponseEntity.ok(visitanteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Visitante adicionar(@Valid @RequestBody Visitante visitante) {
        return cadastroVisitanteService.salvar(visitante);
    }

    @PutMapping("/{visitanteId}")
    public ResponseEntity<Visitante> atualizar(@PathVariable Long visitanteId, @Valid @RequestBody Visitante visitante) {
        if (!visitanteRepository.existsById(visitanteId)) {
            return ResponseEntity.notFound().build();
        }
        visitante.setId(visitanteId);
        visitante = cadastroVisitanteService.salvar(visitante);

        return ResponseEntity.ok(visitante);
    }

    @DeleteMapping("/{visitanteCpf}")
    public ResponseEntity<Visitante> excluir(@PathVariable String visitanteCpf) {
        if (!visitanteRepository.existsByCpf(visitanteCpf)) {
            return ResponseEntity.notFound().build();
        }
        cadastroVisitanteService.excluir(visitanteCpf);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
          return ResponseEntity.badRequest().body(e.getMessage());
    }

}

