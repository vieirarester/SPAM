/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.DisciplinaImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.Criptografia;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Horario;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    Dao disciplinaDao = null;

    private Monitor monitor;
    private Horario horario;
    private Usuario usuario;
    private Disciplina disciplina;

    @PostConstruct
    public void inicializar() {
        monitorDao = new MonitorImplDao();
        alunoDao = new AlunoImplDao();
        disciplinaDao = new DisciplinaImplDao();
        monitor = new Monitor();
        horario = new Horario();
        usuario = new Usuario();
        disciplina = new Disciplina();
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
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
            if (disciplina.getId()!=0) {
                monitor.setHorarios(this.monitor.getHorarios());
                monitor.setDisciplina(disciplina);

                String senhaCripto = Criptografia.criptografar(monitor.getUsuario().getSenha());
                monitor.getUsuario().setSenha(senhaCripto);

                this.monitorDao.inserir(monitor);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O monitor foi cadastrado com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Monitor não pode ser cadastrado sem disciplina!"));
            }
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
        return (List<Horario>) this.monitor.getHorarios();
    }

    public List<Disciplina> recuperarTodosDisciplina() {
        return this.disciplinaDao.recuperarTodos();
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

        if (entrada[0] < saida[0] && horario.getDia() != null) {
            return true;
        } else if (entrada[1] < saida[1] && horario.getDia() != null && entrada[0] == saida[0]) {
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
            m.setHorarios((Set<Horario>) new ArrayList<Horario>());
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Formato de horário inválido. Hora de entrada posterior a hora de saída!"));

            }

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
