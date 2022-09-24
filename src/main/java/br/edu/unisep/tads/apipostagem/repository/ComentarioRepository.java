package br.edu.unisep.tads.apipostagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.apipostagem.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {}
