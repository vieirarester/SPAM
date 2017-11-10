/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.MonitorDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Monitor;
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
    
    @PostConstruct
    public void inicializar(){
        monitorDao = new MonitorImplDao();
        monitor = new Monitor();
    }
    
    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String inserirMonitor(Monitor monitor) {

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
    
}
