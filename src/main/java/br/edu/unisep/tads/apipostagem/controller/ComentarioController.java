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

import br.edu.unisep.tads.apipostagem.repository.ComentarioRepository;
import br.edu.unisep.tads.apipostagem.exception.ResourceNotFoundException;
import br.edu.unisep.tads.apipostagem.model.Comentario;

@RestController
@RequestMapping("api/V1")
public class ComentarioController {
 
    @Autowired
    private ComentarioRepository comentarioRepository;

    // Busca todos os comentarios
    @GetMapping("/comentario")
    public List<Comentario> getAllComentario(){

        return comentarioRepository.findAll();
    }

    @GetMapping("/comentario/{id}")
    public ResponseEntity<Comentario> getComentarioId(
        @PathVariable(value = "id") Long comentarioId) throws ResourceNotFoundException{

            // Se encontrar o comentario retorna o id se não retorna a mensagem
            Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrada " + comentarioId));

            return ResponseEntity.ok().body(comentario);    
    }

    // Criar comentario 
    @PostMapping("/comentario")
    public Comentario createComentario(@RequestBody Comentario comentario){

        return comentarioRepository.save(comentario);
    }

    // Atualiza um comentario 
    @PutMapping("/comentario/{id}")
    public ResponseEntity<Comentario> updateComentario(
        @PathVariable(value = "id") Long comentarioId,
        @RequestBody Comentario detalhes) throws ResourceNotFoundException {

            Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrada " + comentarioId));
            comentario.setAutor(detalhes.getAutor());
            comentario.setTexto(detalhes.getTexto());
            comentario.setAlteradoEm(new Date());
            Comentario comentarioUpdate = comentarioRepository.save(comentario);

            return ResponseEntity.ok().body(comentarioUpdate);
    }

    // Deletando um comentario 
    @DeleteMapping("/comentario/{id}")
    public Map<String, Boolean> deleteComentario(
        @PathVariable(value = "id") Long comentarioId) throws Exception{

        Comentario comentario = comentarioRepository.findById(comentarioId)
            .orElseThrow(() -> new ResourceNotFoundException("Comentario não encontrada " + comentarioId));
        comentarioRepository.delete(comentario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }    
}
