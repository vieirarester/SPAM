/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.AlunoDao;
import br.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
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
    
    UsuarioDao usuarioDao = null;
    AlunoDao alunoDao = null;
    
    private Usuario usuario;
    private Aluno aluno;
    
    @PostConstruct
    public void inicializar() {
        usuarioDao = new UsuarioImplDao();
        usuario = new Usuario();
        alunoDao = new AlunoImplDao();
        aluno = new Aluno();
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
    
    public String realizarLogin(String login, String senha) {
        Aluno aluno = null;

        aluno = this.alunoDao.recuperarLogin(login);

        if (aluno != null) {
            if (aluno.getUsuario().getSenha().equals(senha)) {
                this.setAlunoLogado(aluno);
            } else {
                aluno = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login ou Senha Inválidos!"));
            }
        } else {
            aluno = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login Inválido!"));
        }

        return "/alunoIndex.xhtml";
    }

    public String realizarLogout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/index.xhtml";
    }

}
