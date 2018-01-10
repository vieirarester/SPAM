/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.builders;

import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Resposta;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ester
 */
@ManagedBean
@RequestScoped
public class BuilderPublicacao {
    
    private long id;
    private String titulo;
    private String mensagem;
    private Disciplina disciplina;
    private Set<Resposta> respostas;
    
    public BuilderPublicacao(){
    }
    
    public Publicacao construirPublicacao(){
        return new Publicacao(id, titulo, mensagem, disciplina, respostas);
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
}
