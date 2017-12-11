/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ester
 */
@Entity
public class Publicacao {
    
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private List<Resposta> respostas;
    
    public Publicacao(){
        
    }
    
    public Publicacao(long id, List<Resposta> respostas){
        this.id=id;
        this.respostas=respostas;
    }

    public long getId() {
        return id;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
    
    
}
