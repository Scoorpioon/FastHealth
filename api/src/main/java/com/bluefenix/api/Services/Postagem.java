package com.bluefenix.api.Services;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "postagem")
public class Postagem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostagem;

    @Column(name = "titulo", length = 40, nullable = false)
    private String titulo;

    /* @Column(name = "imagem", nullable = false)
    private String imagem; */

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "nivel_transtorno", nullable = false)
    private int nivelTranstorno;

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNivelTranstorno() {
        return nivelTranstorno;
    }

    public void setNivelTranstorno(int nivelTranstorno) {
        this.nivelTranstorno = nivelTranstorno;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPostagem == null) ? 0 : idPostagem.hashCode());
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + nivelTranstorno;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Postagem other = (Postagem) obj;
        if (idPostagem == null) {
            if (other.idPostagem != null)
                return false;
        } else if (!idPostagem.equals(other.idPostagem))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (nivelTranstorno != other.nivelTranstorno)
            return false;
        return true;
    }
}