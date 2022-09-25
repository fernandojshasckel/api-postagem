package br.edu.unisep.tads.apipostagem.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unisep.tads.apipostagem.repository.PostagemRepository;
import br.edu.unisep.tads.apipostagem.exception.ResourceNotFoundException;
import br.edu.unisep.tads.apipostagem.model.Postagem;


@RestController
@RequestMapping("api/V1")
public class PostagemController {
    
    @Autowired
    private PostagemRepository postagemRepository;

    // Busca todas as postagens
    @GetMapping("/postagem")
    public List<Postagem> getAllPostagem(){

        return postagemRepository.findAll();
    }

    // Busca postagem conforme parametro
    @GetMapping("/postagem/{id}")
    public ResponseEntity<Postagem> getPostagemId(
        @PathVariable(value = "id") Long postagemId) throws ResourceNotFoundException{

            // Se encontrar a postagem retorna o id se não retorna a mensagem
            Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada " + postagemId));

            return ResponseEntity.ok().body(postagem);    
    }

    // Criar postagem 
    @PostMapping("/postagem")
    public Postagem createPostagem(@RequestBody Postagem postagem){

        return postagemRepository.save(postagem);
    }

    // Atualiza postagem 
    @PutMapping("/postagem/{id}")
    public ResponseEntity<Postagem> updatePostagem(
        @PathVariable(value = "id") Long postagemId,
        @RequestBody Postagem detalhes) throws ResourceNotFoundException {

            Postagem postagem = postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada " + postagemId));
            postagem.setTitulo(detalhes.getTitulo());
            postagem.setTexto(detalhes.getTexto());
            postagem.setFoto(detalhes.getFoto());
            postagem.setAutor(detalhes.getAutor());
            postagem.setAlteradoEm(new Date());
            Postagem postagemUpdate = postagemRepository.save(postagem);

            return ResponseEntity.ok().body(postagemUpdate);
    }

    // Deletando uma postagem
    @DeleteMapping("/postagem/{id}")
    public Map<String, Boolean> deletePostagem(
        @PathVariable(value = "id") Long postagemId) throws Exception{

        Postagem postagem = postagemRepository.findById(postagemId)
            .orElseThrow(() -> new ResourceNotFoundException("Postagem não encontrada " + postagemId));
        postagemRepository.delete(postagem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
