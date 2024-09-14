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
@Table(name = "comentario")
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPostagem;

    @Column(name = "texto", columnDefinition = "TEXT", nullable = false)
    private String texto;

    /* @Column(name = "imagem", nullable = false)
    private String imagem; */

    @Temporal(TemporalType.TIMESTAMP)
    @Column (name = "data_publicado")
    private Date dataPublicado;

    public Long getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Long idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDataPublicado() {
        return dataPublicado;
    }

    public void setDataPublicado(Date dataPublicado) {
        this.dataPublicado = dataPublicado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPostagem == null) ? 0 : idPostagem.hashCode());
        result = prime * result + ((texto == null) ? 0 : texto.hashCode());
        result = prime * result + ((dataPublicado == null) ? 0 : dataPublicado.hashCode());
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
        Comentario other = (Comentario) obj;
        if (idPostagem == null) {
            if (other.idPostagem != null)
                return false;
        } else if (!idPostagem.equals(other.idPostagem))
            return false;
        if (texto == null) {
            if (other.texto != null)
                return false;
        } else if (!texto.equals(other.texto))
            return false;
        if (dataPublicado == null) {
            if (other.dataPublicado != null)
                return false;
        } else if (!dataPublicado.equals(other.dataPublicado))
            return false;
        return true;
    }
}