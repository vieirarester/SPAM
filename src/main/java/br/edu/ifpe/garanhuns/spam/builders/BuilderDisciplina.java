/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.builders;

import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ester
 */
@ManagedBean
@RequestScoped
public class BuilderDisciplina {

    private long id;
    private String nome;
    
    public BuilderDisciplina(){
        
    }
    
    public Disciplina construirDisciplina(){
        return new Disciplina(id, nome);
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
    
    
}
