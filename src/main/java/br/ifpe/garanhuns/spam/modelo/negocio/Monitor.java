/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.modelo.negocio;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author 20141D120122
 */
@Entity
@DiscriminatorValue(value = "M")
public class Monitor extends Usuario{

    @Column
    private String disciplina;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Horario> horarios;
    
    public Monitor() {
    }
    
    public Monitor(long id, String nome, String login, String senha, String disciplina, List<Horario> horarios) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.horarios = horarios;
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
    
    public String toString(){
        return ("MonitorControlador: id:"+id+", nome:"+nome+", login:"+login+ ", senha:"+senha+ ", disciplina:"+disciplina+ ", horários:"+horarios+"}");
    }
    
}
