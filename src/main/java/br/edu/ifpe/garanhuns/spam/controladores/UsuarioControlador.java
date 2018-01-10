/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.PublicacaoDao;
import br.edu.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.PublicacaoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.Criptografia;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
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
 * @author João Witor
 */
@ManagedBean(name = "usuarioControlador")
@SessionScoped
public class UsuarioControlador {

    UsuarioDao usuarioDao;
    AlunoDao alunoDao;
    MonitorDao monitorDao;
    PublicacaoDao publicacaoDao;

    private Usuario usuario;
    private Aluno aluno;
    private Monitor monitor;
    private Publicacao publicacao;

    @PostConstruct
    public void inicializar() {
        usuarioDao = new UsuarioImplDao();
        usuario = new Usuario();
        alunoDao = new AlunoImplDao();
        aluno = new Aluno();
        monitorDao = new MonitorImplDao();
        monitor = new Monitor();
        publicacaoDao = new PublicacaoImplDao();
        publicacao = new Publicacao();
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

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
    
    public List<Publicacao> recuperarTodasPublicacoes(){
        return this.publicacaoDao.recuperarTodos();
    }

    public void setUsuarioLogado(Usuario usuario) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
    }

    public Usuario getUsuarioLogado() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("usuarioLogado");
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
            if (this.alunoDao.recuperarLogin(login)!=null) {

                Aluno a = this.alunoDao.recuperarLogin(login);

                if (a != null) {
                    String senhaCripto = Criptografia.criptografar(senha);
                    if (a.getUsuario().getSenha().equals(senhaCripto)) {
                        this.setAlunoLogado(a);
                        this.setUsuarioLogado(this.getAlunoLogado().getUsuario());
                        return "alunoIndex.xhtml";

                    } else {
                        a = null;
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!", "Senha inválida"));
                    }
                } else {
                    a = null;
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido!", "Login inválido"));
                }
            }

            else if (this.monitorDao.recuperarLogin(login)!=null) {

                Monitor m = this.monitorDao.recuperarLogin(login);

                if (m != null) {
                    String senhaCripto = Criptografia.criptografar(senha);
                    if (m.getUsuario().getSenha().equals(senhaCripto)) {
                        this.setMonitorLogado(m);
                        this.setUsuarioLogado(this.getMonitorLogado().getUsuario());
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

    public boolean validarPublicacao(Publicacao publicacao) {
        boolean jaExiste;

        if (getAlunoLogado().getUsuario().getPublicacoes().contains(publicacao)) {
            jaExiste = true;
        } else {
            jaExiste = false;
        }
        if (getMonitorLogado().getUsuario().getPublicacoes().contains(publicacao)) {
            jaExiste = true;
        } else {
            jaExiste = false;
        }
        return jaExiste;
    }

    public String inserirPublicacao() {
        Aluno alunoLog = this.getAlunoLogado();
        
        if (alunoLog != null) {
            Publicacao pub = new Publicacao();
            pub.setTitulo(this.publicacao.getTitulo());
            pub.setMensagem(this.publicacao.getMensagem());

            if (alunoLog.getUsuario().getPublicacoes() == null) {
                alunoLog.getUsuario().setPublicacoes((Set<Publicacao>) new ArrayList<Publicacao>());
            }

            boolean existe = false;

            for (Publicacao p : alunoLog.getUsuario().getPublicacoes()) {
                if (p.equals(pub)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                alunoLog.getUsuario().getPublicacoes().add(pub);
                alunoDao.atualizar(alunoLog);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação duplicada!"));
            }

        }
        Monitor monitorLog = this.getMonitorLogado();
        
        if (monitorLog != null) {
            Publicacao pub = new Publicacao();
            pub.setTitulo(this.publicacao.getTitulo());
            pub.setMensagem(this.publicacao.getMensagem());

            if (monitorLog.getUsuario().getPublicacoes() == null) {
                monitorLog.getUsuario().setPublicacoes((Set<Publicacao>) new ArrayList<Publicacao>());
            }
            
            boolean existe = false;

            for (Publicacao p : monitorLog.getUsuario().getPublicacoes()) {
                if (p.equals(pub)) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                monitorLog.getUsuario().getPublicacoes().add(pub);
                monitorDao.atualizar(monitorLog);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação duplicada!"));
            }
        }
        return "";
    }

    public String removerPublicacao(Publicacao publicacao) {
        Aluno alunoLog = this.getAlunoLogado();
        Monitor monitorLog = this.getMonitorLogado();
        if(alunoLog!=null){
           alunoLog.getUsuario().getPublicacoes().remove(publicacao); 
           alunoDao.atualizar(alunoLog);
        } else if(monitorLog!=null){
            monitorLog.getUsuario().getPublicacoes().remove(publicacao);
            monitorDao.atualizar(monitorLog);
        }
        return "";
    }

}
