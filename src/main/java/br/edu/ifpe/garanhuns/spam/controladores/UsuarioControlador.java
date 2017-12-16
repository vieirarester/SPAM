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
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author João Witor
 */
@ManagedBean(name = "usuarioControlador")
@SessionScoped
public class UsuarioControlador {

    UsuarioDao usuarioDao;
    AlunoDao alunoDao;
    MonitorDao monitorDao;

    private Usuario usuario;
    private Aluno aluno;
    private Monitor monitor;

    @PostConstruct
    public void inicializar() {
        usuarioDao = new UsuarioImplDao();
        usuario = new Usuario();
        alunoDao = new AlunoImplDao();
        aluno = new Aluno();
        monitorDao = new MonitorImplDao();
        monitor = new Monitor();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setUsuarioLogado(Usuario usuario) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
    }

    private void setAlunoLogado(Aluno aluno) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alunoLogado", aluno);
    }

    public Aluno getAlunoLogado() {
        return (Aluno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("alunoLogado");
    }

    private void setMonitorLogado(Monitor monitor) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("monitorLogado", monitor);
    }
    
     public Monitor getMonitorLogado() {
        return (Monitor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("monitorLogado");
    }

    public String realizarLogin(String login, String senha) {
        if (!login.isEmpty() && !senha.isEmpty()) {
            if (this.alunoDao != null) {

                Aluno a = this.alunoDao.recuperarLogin(login);

                if (a != null) {
                    if (a.getUsuario().getSenha().equals(senha)) {
                        this.setAlunoLogado(a);
                        return "alunoIndex.xhtml";

                    } else {
                        a = null;
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Senha inválida"));
                    }
                } else {
                    a = null;
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Login inválido"));
                }
            }

            if (this.monitorDao != null) {

                Monitor m = this.monitorDao.recuperarLogin(login);

                if (m != null) {
                    if (m.getUsuario().getSenha().equals(senha)) {
                        this.setMonitorLogado(m);
                        return "monitorIndex.xhtml";
                    } else {
                        m = null;
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos!", "Senha inválida"));
                    }
                } else {
                    m = null;
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos!", "Login inválido"));
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login e Senha são obrigatórios!", "Falha no login!"));
        }
        return "";
    }

    public String realizarLogout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/index.xhtml";
    }

}
