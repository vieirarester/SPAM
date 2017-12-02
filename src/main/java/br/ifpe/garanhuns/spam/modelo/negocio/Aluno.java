/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Ester
 */
@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Usuario {

    @Column
    private String curso;

    public Aluno(long id, String nome, String login, String senha, String curso) {
        this.curso = curso;
    }

    public Aluno() {
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void alterar(Aluno t) {
        if (t == null) {
            return;
        }
        this.setLogin(t.getLogin());
        this.setSenha(t.getSenha());
        this.setNome(t.getNome());
    }

    public boolean autenticar(String password) {
        return this.senha.equals(password);
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
