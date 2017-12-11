/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ester
 */
@Entity
class Resposta {
    
    @Id
    private long id;
    @Column
    private String descricao;
    
    public Resposta(){
        
    }
    
    public Resposta(long id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }  
}
