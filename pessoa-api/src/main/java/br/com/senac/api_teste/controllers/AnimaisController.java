package br.com.senac.api_teste.controllers;

import br.com.senac.api_teste.DTOS.AnimaisRequestDTO;
import br.com.senac.api_teste.DTOS.PessoasRequestDTO;
import br.com.senac.api_teste.entidades.Animais;
import br.com.senac.api_teste.entidades.Pessoas;
import br.com.senac.api_teste.repositorios.AnimaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Animais")
public class AnimaisController {

    private AnimaisRepository animaisRepository;

    public AnimaisController(AnimaisRepository animaisRepository) { this.animaisRepository = animaisRepository; }

    @GetMapping("/listar")
    public ResponseEntity<List<Animais>> listar(){
        List<Animais> animaisList = animaisRepository.findAll();

        if (animaisList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }

        return ResponseEntity.ok(animaisList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Animais> criar(@RequestBody AnimaisRequestDTO animal) {

        Animais animalPersist = new Animais();
        animalPersist.setNome(animal.getNome());
        animalPersist.setRaca(animal.getRaca());
        animalPersist.setPeso(animal.getPeso());

        Animais retorno = animaisRepository.save(animalPersist);

        return ResponseEntity.ok(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Animais> atualizar(@RequestBody AnimaisRequestDTO animal, @PathVariable Long id){

        if (animaisRepository.existsById(id)) {
            Animais animalPersist = new Animais();
            animalPersist.setNome(animal.getNome());
            animalPersist.setRaca(animal.getRaca());
            animalPersist.setPeso(animal.getPeso());
            animalPersist.setId(id);

            Animais retorno = animaisRepository.save(animalPersist);

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        if (animaisRepository.existsById(id)){
            animaisRepository.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }


}
