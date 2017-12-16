/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo.negocio;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author 20141D120122
 */
@Entity
public class Monitor{
    
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nome;
    @Embedded
    private Usuario usuario;
    @Column
    private String disciplina;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Horario> horarios;
    
    public Monitor() {
    }
    
    public Monitor(long id, String nome, Usuario usuario, String disciplina, List<Horario> horarios) {
        this.nome = nome;
        this.usuario = usuario;
        this.disciplina = disciplina;
        this.horarios = horarios;
    }
    
    public long getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    
    public List<Horario> getHorarios() {
        return horarios;
    }
    
    public void setHorarios(List<Horario> horario) {
        this.horarios = horario;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Monitor other = (Monitor) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.usuario.getLogin(), other.usuario.getLogin())) {
            return false;
        }
        if (!Objects.equals(this.usuario.getSenha(), other.usuario.getSenha())) {
            return false;
        }
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        if (!Objects.equals(this.horarios, other.horarios)) {
            return false;
        }
        return true;
    }
    
    public void alterar(Monitor t) {
        if (t == null) {
            return;
        }
        this.usuario.setLogin(t.usuario.getLogin());
        this.usuario.setSenha(t.usuario.getSenha());
        this.setNome(t.getNome());
        this.setDisciplina(t.getDisciplina());
        this.setHorarios(t.getHorarios());
    }
    
    public boolean autenticar(String senha) {
        return this.usuario.getSenha().equals(senha);
    }
}