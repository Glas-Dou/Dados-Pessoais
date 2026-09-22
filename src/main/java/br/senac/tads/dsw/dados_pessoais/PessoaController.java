package br.senac.tads.dsw.dados_pessoais;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> obterPessoas() {
        return pessoaService.obterPessoas();
    }

    @GetMapping("/{username}")
    public Pessoa obterPessoa(@PathVariable("username") String username) {
        Optional<Pessoa> optPessoa = pessoaService.obterPessoa(username);
        if (optPessoa.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optPessoa.get();
    }

    
    @PostMapping("/sem-validacao")
    public ResponseEntity<?> incluirNovo(@RequestBody Pessoa pessoa) {
        pessoaService.incluirNovaPessoa(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pessoas/{username}")
                .buildAndExpand(pessoa.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping
    public ResponseEntity<?> incluirNovoComValidacao(@RequestBody @Valid Pessoa pessoa) {
        pessoaService.incluirNovaPessoa(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pessoas/{username}")
                .buildAndExpand(pessoa.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    
    @GetMapping("/revisao")
    public Map<String, String> revisao() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "IA");
        resposta.put("data", "2026-09-18");
        return resposta;
    }
}