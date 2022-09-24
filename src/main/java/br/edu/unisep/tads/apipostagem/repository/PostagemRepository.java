package br.edu.unisep.tads.apipostagem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unisep.tads.apipostagem.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{}
