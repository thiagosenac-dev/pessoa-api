package br.com.senac.api_teste.controllers;

import br.com.senac.api_teste.DTOS.PessoasRequestDTO;
import br.com.senac.api_teste.entidades.Pessoas;
import br.com.senac.api_teste.repositorios.PessoasRepository;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/pessoas")
public class PessoasController {

    private PessoasRepository pessoasRepository;

    public PessoasController(PessoasRepository pessoasRepository) {
        this.pessoasRepository = pessoasRepository;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoas>> listar(){
        List<Pessoas> pessoasList = pessoasRepository.findAll();

        if (pessoasList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(pessoasList);
    }
    @PostMapping("/criar")
    public ResponseEntity<Pessoas> criar(@RequestBody PessoasRequestDTO pessoa) {

        Pessoas pessoaPersist = new Pessoas();
        pessoaPersist.setNome(pessoa.getNome());
        pessoaPersist.setSobrenome(pessoa.getSobrenome());

        Pessoas retorno = pessoasRepository.save(pessoaPersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoas> atualizar(@RequestBody PessoasRequestDTO pessoa, @PathVariable Long id){

        if (pessoasRepository.existsById(id)) {
            Pessoas pessoaPersist = new Pessoas();
            pessoaPersist.setNome(pessoa.getNome());
            pessoaPersist.setSobrenome(pessoa.getSobrenome());
            pessoaPersist.setId(id);

            Pessoas retorno = pessoasRepository.save(pessoaPersist);

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (pessoasRepository.existsById(id)){
            pessoasRepository.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

}
























