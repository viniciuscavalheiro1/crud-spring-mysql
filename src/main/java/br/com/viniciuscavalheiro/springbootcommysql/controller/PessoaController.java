package br.com.viniciuscavalheiro.springbootcommysql.controller;

import br.com.viniciuscavalheiro.springbootcommysql.controller.dto.PessoaRq;
import br.com.viniciuscavalheiro.springbootcommysql.controller.dto.PessoaRs;
import br.com.viniciuscavalheiro.springbootcommysql.model.Pessoa;
import br.com.viniciuscavalheiro.springbootcommysql.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    @GetMapping("/")
    public ResponseEntity<List<PessoaRs>> findAll() {
        var pessoas = pessoaRepository.findAll();
        if(pessoas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoas
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaRs> findById(@PathVariable("id") Long id) {
        var pessoa = pessoaRepository.getReferenceById(id);

        return ResponseEntity.ok(PessoaRs.converter(pessoa));
    }

    @PostMapping("/")
    public ResponseEntity<Pessoa> savePerson(@RequestBody PessoaRq pessoa) {
        var p = new Pessoa();
        p.setNome(pessoa.getNome());
        p.setSobrenome(pessoa.getSobrenome());
        pessoaRepository.save(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePerson(@PathVariable("id") Long id, @RequestBody PessoaRq pessoa) throws Exception {
        var p = pessoaRepository.findById(id);

        if(p.isPresent()) {
            var pessoaSave = p.get();
            pessoaSave.setNome(pessoa.getNome());
            pessoaSave.setSobrenome(pessoa.getSobrenome());
            return ResponseEntity.ok(pessoaRepository.save(pessoaSave));
        }
        return ResponseEntity.of(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> DeletePerson(@PathVariable("id") Long id) {
        var p = pessoaRepository.findById(id);
        if(p.isPresent()) {
            pessoaRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.of(p);
    }
}
