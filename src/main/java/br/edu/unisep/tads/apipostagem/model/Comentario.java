package br.edu.unisep.tads.apipostagem.model;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comentario")
public class Comentario {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @Column(name = "autor", nullable = false, length = 75)
    private String autor;

    @Column(name = "texto", nullable = false, length = 255)
    private String texto;
    
    @Column(name = "data", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(name = "criado_em", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date criadoEm;
    
    @Column(name = "criado_por", nullable = false)
    @CreatedBy
    private String criadoPor;
    
    @Column(name = "alteradoEm", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date alteradoEm;

    @Column(name = "alteradoPor", nullable = false)
    @LastModifiedBy
    private String alteradoPor;
}
