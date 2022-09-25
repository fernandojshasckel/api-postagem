package br.edu.unisep.tads.apipostagem.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "postagem")
public class Postagem {
    
    public Postagem() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "titulo", nullable = false, length = 75)
    private String titulo;

    @Column(name = "texto", nullable = false, length = 255)
    private String texto;

    @Column(name = "foto", nullable = false)
    private String foto;

    @Column(name = "autor", nullable = false)
    private String autor;

    @OneToMany(mappedBy = "postagem",
        targetEntity = Comentario.class,
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comentario> comentarios;

    @Column(name = "criado_em", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date criadoEm;

    @Column(name = "criado_por", nullable = false)
    @CreatedBy
    private String criadoPor;

    @Column(name = "alterado_em", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date alteradoEm;

    @Column(name = "alteradoPor", nullable = false)
    @LastModifiedBy
    private String alteradoPor; 
}
