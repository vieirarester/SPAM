/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author 20141D12GR0076
 */
@Table
@Entity
public class Aluno extends Usuario{
    @Column
    final private int tipo;
    @Column
    private String nome;

    public Aluno(long id, String nome, String login, String senha) {
        this.tipo = 2;
        this.nome = nome;
    }

    public  Aluno() {
        this.tipo = 2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
