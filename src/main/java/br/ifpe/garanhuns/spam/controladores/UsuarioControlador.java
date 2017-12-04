/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.AlunoDao;
import br.ifpe.garanhuns.spam.dao.MonitorDao;
import br.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
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

    private Usuario usuario;

    @PostConstruct
    public void inicializar() {
        usuarioDao = new UsuarioImplDao();
        usuario = new Usuario();
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

    public String realizarLogin(String login, String senha) {
        String retorno = "";
        Usuario usuario = null;

        usuario = this.usuarioDao.recuperarLogin(login);

        if (usuario != null) {
            if (usuario.getSenha().equals(senha)) {
                this.setUsuarioLogado(usuario);
                //verificar o tipo do usuario para saber pra qual página retornar
                retorno = "";
            } else {
                usuario = null;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login ou Senha Inválidos!"));
            }
        } else {
            usuario = null;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no Login!", "Login Inválido!"));
        }

        return retorno;
    }

    public String realizarLogout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/index.xhtml";
    }
}
