/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Ester
 */
@Embeddable
public class Usuario {

    @Column(unique = true)
    private String login;
    @Column
    private String senha;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Publicacao> publicacoes;
    
    public Usuario() {

    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
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

    public Set<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(Set<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
    
    
}
