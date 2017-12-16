/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Horario;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import java.util.ArrayList;
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
public class MonitorControlador {

    MonitorDao monitorDao = null;

    private Monitor monitor;
    private Horario horario;
    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        monitorDao = new MonitorImplDao();
        monitor = new Monitor();
        horario = new Horario();
        usuario = new Usuario();
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String inserirMonitor(Monitor monitor) {

        monitor.setHorarios(this.monitor.getHorarios());
        
        this.monitorDao.inserir(monitor);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O monitor foi cadastrado com sucesso!"));
        return "";
    }

    public void removerMonitor(Monitor monitor) {
        this.monitorDao.deletar(monitor);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O monitor foi deletado com sucesso!"));

    }

    public String alterarMonitor(Monitor monitor) {
        this.monitorDao.atualizar(monitor);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O monitor foi alterado com sucesso!"));
        return "";
    }

    public Monitor recuperarMonitor(long id) {
        return this.monitorDao.recuperar(id);
    }

    public List<Monitor> recuperarTodosMonitor() {
        return this.monitorDao.recuperarTodos();
    }
    
    public List<Horario> recuperarTodosHorario(){
        return this.monitor.getHorarios();
    }

    public String inserirHorario() {

        Monitor m = this.getMonitor();

        Horario h = new Horario();
        h.setDia(this.horario.getDia());
        h.setHoraInicio(this.horario.getHoraInicio());
        h.setHoraFim(this.horario.getHoraFim());

        if (m.getHorarios() == null) {
            m.setHorarios(new ArrayList<Horario>());
        }

        boolean existe = false;
        for (Horario hor : m.getHorarios()) {
            if (hor.getDia().equals(this.horario.getDia()) && hor.getHoraInicio().equals(this.horario.getHoraInicio()) && hor.getHoraFim().equals(this.horario.getHoraFim())) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            m.getHorarios().add(h);
            this.setMonitor(m);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Horário já cadastrado!"));
        }

        return "";
    }

    public String removeHorario(Horario horario) {
        Monitor m = this.getMonitor();
        m.getHorarios().remove(horario);
        this.setMonitor(m);
        return "";
    }
}