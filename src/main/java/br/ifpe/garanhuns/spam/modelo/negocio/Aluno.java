/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Ester
 */
@Entity
public class Aluno {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nome;
    @Column
    private String curso;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Aluno() {
    }
    
    public Aluno(long id, String nome, String curso, Usuario usuario){
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.usuario = usuario;
    }

    public Aluno(String nome, String curso, Usuario usuario) {
        this.nome = nome;
        this.curso = curso;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void alterar(Aluno t) {
        if (t == null) {
            return;
        }
        this.getUsuario().setLogin(t.usuario.getLogin());
        this.getUsuario().setSenha(t.usuario.getSenha());
        this.setNome(t.getNome());
    }

    public boolean autenticar(String password) {
        return this.usuario.getSenha().equals(password);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
