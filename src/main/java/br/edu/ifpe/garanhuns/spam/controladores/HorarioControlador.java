/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.HorarioImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Horario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ester
 */
@ManagedBean
@SessionScoped
public class HorarioControlador {
    
    Dao horarioDao = null;
    
    private Horario horario;
    
    @PostConstruct
    public void inicializar(){
        horarioDao = new HorarioImplDao();
        horario = new Horario();
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void deletarHorario(Horario horario) {
        
    }

    public String atualizarHorario(Horario horario) {
        this.horarioDao.atualizar(horario);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O horário foi atualizado com sucesso!"));
        return "";
    }

    public Horario recuperarHorario(long id) {
        return (Horario) this.horarioDao.recuperar(id);
    }

    public List<Horario> recuperarTodosHorario() {
        return this.horarioDao.recuperarTodos();
    }
}
