/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import br.edu.ifpe.garanhuns.spam.conversores.entidade.SampleEntity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Ester
 */
@Entity
public class Disciplina implements Serializable, SampleEntity{
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Monitor> monitores;
    
    public Disciplina(){
        
    }
    
    public Disciplina(long id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    @Override
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Monitor> getMonitores() {
        return monitores;
    }

    public void setMonitores(Set<Monitor> monitores) {
        this.monitores = monitores;
    }
    
    @Override
    public boolean equals(Object obj){
        return this.id == ((Disciplina)obj).id;
    }
}
