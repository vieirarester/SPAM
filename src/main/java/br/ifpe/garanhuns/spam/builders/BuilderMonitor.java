/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.builders;

import br.ifpe.garanhuns.spam.modelo.negocio.Horario;
import br.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ester
 */
@ManagedBean
@RequestScoped
public class BuilderMonitor {
    
    private long id;
    private String nome;
    private Usuario usuario;
    private String disciplina;
    private List<Horario> horarios;

    public BuilderMonitor() {
    }
    
    public Monitor construirMonitor() {
        return new Monitor(id, nome, usuario, disciplina, horarios);
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
