/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.UsuarioImplDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
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
public class UsuarioControlador {
    
    UsuarioDao usuarioDao = null;
    
    private Usuario usuario;
    
    @PostConstruct
    public void inicializar(){
        usuarioDao = new UsuarioImplDao();
        usuario = new Usuario();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String inserirUsuario(Usuario usuario) {

        this.usuarioDao.inserir(usuario);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O usuário foi cadastrado com sucesso!"));
        return "";
    }

    public void deletarUsuario(Usuario usuario) {
        this.usuarioDao.deletar(usuario);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O usuário foi deletado com sucesso!"));

    }

    public String atualizarUsuario(Usuario usuario) {
        this.usuarioDao.atualizar(usuario);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O usuário foi atualizado com sucesso!"));
        return "";
    }

    public Usuario recuperarUsuario(long id) {
        return this.usuarioDao.recuperar(id);
    }

    public List<Usuario> recuperarTodosUsuario() {
        return this.usuarioDao.recuperarTodos();
    }
    
}
