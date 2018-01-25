/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ester
 */
@Entity
public class Publicacao {
    
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String titulo;
    @Column
    private String mensagem;
    @OneToOne
    private Disciplina disciplina;
    @OneToMany (cascade = CascadeType.ALL)
    private Set<Resposta> respostas;
    
    public Publicacao(){
        
    }
    
    public Publicacao(long id, String titulo, String mensagem, Disciplina disciplina, Set<Resposta> respostas){
        this.id=id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.disciplina = disciplina;
        this.respostas=respostas;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        
        final Publicacao other = (Publicacao) obj;
        
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        return true;
    }
}
