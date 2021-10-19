package com.digitalhouse.blog.controller;

import com.digitalhouse.blog.model.Postagem;
import com.digitalhouse.blog.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens") //endpoint
@CrossOrigin("*") //qualquer origem vai ser aceita
public class PostagemController {

    @Autowired //o spring faz a injeção de dependencia sozinho pq aqui é uma interface
    private PostagemRepository repository; //Injetando o repositório

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}") //monta uma url onde indicamos o id do item desejado
    public ResponseEntity<Postagem> getById(@PathVariable Long id){
        //precisamos usar dentro da passagem do paramentro a anotação PathVariable pra dizer
        //ao Spring que cuide da parte de trazer da url a informação e use com parametro.
        //vamos precisar usar um optional e expressão lambda
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto){
        return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        repository.deleteById(id);
    }

}