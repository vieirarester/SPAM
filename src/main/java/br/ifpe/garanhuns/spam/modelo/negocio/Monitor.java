/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 20141D120122
 */
@Table
@Entity
public class Monitor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column
    private String nome;
    @Column(nullable = false, unique = true)
    private String login;
    @Column
    private String senha;
    @Column
    private String disciplina;
    @Embedded
    private List<Horario> horarios;
    
    public Monitor() {
    }
    
    public Monitor(long id, String nome, String login, String senha, String disciplina, List<Horario> horarios) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.disciplina = disciplina;
        this.horarios = horarios;
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
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
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
        this.setLogin(t.getLogin());
        this.setSenha(t.getSenha());
        this.setNome(t.getNome());
        this.setDisciplina(t.getDisciplina());
        this.setHorarios(t.getHorarios());
    }
    
    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
    
}
