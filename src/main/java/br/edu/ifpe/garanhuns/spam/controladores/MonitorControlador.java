/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
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
    AlunoDao alunoDao = null;

    private Monitor monitor;
    private Horario horario;
    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        monitorDao = new MonitorImplDao();
        alunoDao = new AlunoImplDao();
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

    public boolean validarUsuario(String login) {
        boolean jaExiste;
        if (monitorDao.recuperarLogin(login) != null || alunoDao.recuperarLogin(login) != null) {
            jaExiste = true;
        } else {
            jaExiste = false;
        }
        return jaExiste;
    }

    public String inserirMonitor(Monitor monitor) {

        if (!validarUsuario(monitor.getUsuario().getLogin())) {
            monitor.setHorarios(this.monitor.getHorarios());

            this.monitorDao.inserir(monitor);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O monitor foi cadastrado com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este usuário já existe!"));
        }
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

    public List<Horario> recuperarTodosHorario() {
        return this.monitor.getHorarios();
    }

    public boolean validarHorario(Horario horario) {
        String[] horaEntrada = horario.getHoraInicio().split(":");
        String[] horaSaida = horario.getHoraFim().split(":");
        int[] entrada = new int[2];
        int[] saida = new int[2];

        for (int i = 0; i < 2; i++) {
            entrada[i] = Integer.parseInt(horaEntrada[i]);
            saida[i] = Integer.parseInt(horaSaida[i]);
        }

        if (entrada[0] < saida[0]) {
            return true;
        } else if (entrada[1] < saida[1]) {
            return true;
        }

        return false;
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
            if (validarHorario(h)) {
                m.getHorarios().add(h);
                this.setMonitor(m);
            } else {
                FacesContext.getCurrentInstance().addMessage("mensagemCadastroMonitor", new FacesMessage("Formato de horário errado. Hora de entrada posterior a hora de saida!"));

            }

        } else {
            FacesContext.getCurrentInstance().addMessage("mensagemCadastroMonitor", new FacesMessage("Horário já cadastrado!"));
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
