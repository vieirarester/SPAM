/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.AlunoDao;
import br.ifpe.garanhuns.spam.dao.MonitorDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
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

    MonitorDao monitorDao = new MonitorImplDao();
    AlunoDao alunoDao = new AlunoImplDao();

    public UsuarioControlador() {
    }

    public void setUsuarioLogado(Usuario usuario) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuario);
    }

    public String realizarLogin(String login, String senha) {
        String retorno = "/CadastroAluno.xhtml";
        Usuario usuario = null;

        usuario = this.monitorDao.recuperarLogin(login);
        if (usuario != null) {
            //Redirecionar Monitor
            if (usuario.getSenha().equals(senha)) {
                this.setUsuarioLogado(usuario);
                retorno = "/MonitorIndex.xhtml?faces-redirect=true";
            } else {
                usuario = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login ou Senha Inválidos!"));
            }
        } else {
            //Redirecionar Aluno
            usuario = this.alunoDao.recuperarLogin(login);
            if (usuario != null) {
                //Redirecionar Monitor
                if (usuario.getSenha().equals(senha)) {
                    this.setUsuarioLogado(usuario);
                    retorno = "/AlunoIndex.xhtml?faces-redirect=true";
                } else {
                    usuario = null;
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login ou Senha Inválidos!"));
                }
            } else {

                usuario = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "login Inválido!"));
            }

            
        }
        return retorno;
    }

    public String realizarLogout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/index.xhtml";
    }
}
