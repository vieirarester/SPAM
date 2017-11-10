/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.builders;

import br.ifpe.garanhuns.spam.modelo.negocio.Administrador;

/**
 *
 * @author Ester
 */
public class BuilderAdministrador {
    
    private long id;
    private String nome;
    private String login;
    private String senha;

    public BuilderAdministrador() {
    }
    
    public Administrador construirAdministrador(){
        return new Administrador(id, nome, login, senha);
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
}
