/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.PublicacaoDao;
import br.edu.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.DisciplinaImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.PublicacaoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.Criptografia;
import br.edu.ifpe.garanhuns.spam.modelo.PublicacaoComparator;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import java.util.ArrayList;
import java.util.Collections;
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
    Dao disciplinaDao;
    
    private Usuario usuario;
    private Aluno aluno;
    private Monitor monitor;
    private Publicacao publicacao;
    private Disciplina disciplina;
    
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
        disciplina = new Disciplina();
        disciplinaDao = new DisciplinaImplDao();
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
    
    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    public List<Publicacao> recuperarTodasPublicacoes() {
        return this.publicacaoDao.recuperarTodos();
    }
    
    public List<Publicacao> recuperarPublicacaoOrdenada(){
        List<Publicacao> lista = new ArrayList<>();
        
        for(Publicacao p:this.publicacaoDao.recuperarTodos()){
            lista.add(p);
        }
        lista.sort(new PublicacaoComparator());
        
        List <Publicacao> listaInversa = new ArrayList<>();
        for(Publicacao p: lista){
            listaInversa.add(p);
        }
        
        Collections.reverse(listaInversa);
        
        return listaInversa;
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
    
    public boolean verificarSeAdmLogado() {
        return getUsuarioLogado().getLogin().equals("adm");
    }
    
    public List<Disciplina> recuperarTodosDisciplina() {
        return this.disciplinaDao.recuperarTodos();
    }
    
    public String recuperarLogin(String login) {
        if (login != null) {
            if (alunoDao.recuperarLogin(login) != null) {
                this.aluno = alunoDao.recuperarLogin(login);
                return "alterarSenha.xhtml";
            } else if (monitorDao.recuperarLogin(login) != null) {
                this.monitor = monitorDao.recuperarLogin(login);
                return "alterarSenha.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este usuário não existe!", "Usuário inválido"));
            }
        }
        return "";
    }
    
    public boolean autenticarSenha(String novaSenha, String confirmacao) {
        if (novaSenha == null || confirmacao == null || novaSenha.isEmpty() || confirmacao.isEmpty()) {
            return false;
        } else if (!novaSenha.equals(confirmacao)) {
            return false;
        }
        return true;
    }
    
    public String redefinirSenha(String novaSenha, String confirmacao) {
        if (autenticarSenha(novaSenha, confirmacao)) {
            if (aluno != null) {
                String novaSenhaCripto = Criptografia.criptografar(novaSenha);
                aluno.getUsuario().setSenha(novaSenhaCripto);
                
                alunoDao.atualizar(aluno);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha redefinida com sucesso!", "Senha redefinida com sucesso!"));
                return "index.xhtml";
            } else if (monitor != null) {
                String novaSenhaCripto = Criptografia.criptografar(novaSenha);
                monitor.getUsuario().setSenha(novaSenhaCripto);
                
                monitorDao.atualizar(monitor);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha redefinida com sucesso!", "Senha redefinida com sucesso!"));
                return "index.xhtml";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas inválidas!", "Senha inválida"));
        }
        return "";
    }
    
    public String realizarLogin(String login, String senha) {
        
        if (!login.isEmpty() && !senha.isEmpty()) {
            if (login.equals("adm")) {
                if (senha.equals("adm")) {
                    Usuario user = new Usuario(login, senha);
                    this.setUsuarioLogado(user);
                    return "administradorIndex.xhtml";
                    
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha incorreta!", "Senha inválida"));
                }
            } else if (this.alunoDao.recuperarLogin(login) != null) {
                
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
            } else if (this.monitorDao.recuperarLogin(login) != null) {
                
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
    
    public String inserirPublicacao(String titulo, String msg) {
        Aluno alunoLog = this.getAlunoLogado();
        Publicacao pub = new Publicacao();
        if (alunoLog != null) {
            if (disciplina.getId() != 0) {
                pub.setTitulo(titulo);
                pub.setMensagem(msg);
                pub.setDisciplina(disciplina);
                
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
                    setAlunoLogado(alunoDao.recuperar(alunoLog.getId()));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação duplicada!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação não pode ser inserida sem disciplina!"));
            }
        }
        Monitor monitorLog = this.getMonitorLogado();
        
        if (monitorLog != null) {
            pub.setTitulo(titulo);
            pub.setMensagem(msg);
            pub.setDisciplina(disciplina);
            
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
                setMonitorLogado(monitorDao.recuperar(monitorLog.getId()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Publicação duplicada!"));
            }
        }
        return "";
    }
    
    public String removerPublicacao(Publicacao publicacao) {
        Aluno alunoLog = this.getAlunoLogado();
        Monitor monitorLog = this.getMonitorLogado();
        if (alunoLog != null) {
            publicacaoDao.deletar(publicacao);
            alunoDao.atualizar(alunoLog);
            setAlunoLogado(alunoDao.recuperar(alunoLog.getId()));
        } else if (monitorLog != null) {
            monitorLog.getUsuario().getPublicacoes().remove(publicacao);
            monitorDao.atualizar(monitorLog);
            setMonitorLogado(monitorDao.recuperar(monitorLog.getId()));
        }
        return "";
    }
    
}
