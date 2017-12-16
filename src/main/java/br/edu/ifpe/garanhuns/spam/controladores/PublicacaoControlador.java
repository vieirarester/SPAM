/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.PublicacaoDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.PublicacaoImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Resposta;
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
public class PublicacaoControlador {
    
    PublicacaoDao publicacaoDao = null;
    
    private Publicacao publicacao;
    private Resposta resposta;
    
    @PostConstruct
    public void inicializar(){
        publicacaoDao = new PublicacaoImplDao();
        publicacao = new Publicacao();
        resposta = new Resposta();
    }
    
    public Publicacao getPublicacao(){
        return publicacao;
    }
    
    public void setPublicacao(Publicacao publicacao){
        this.publicacao = publicacao;
    }
    
    public String inserirPublicacao(Publicacao publicacao) {
        
        this.publicacaoDao.inserir(publicacao);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A publicação foi cadastrada com sucesso!"));
        return "";
    }

    public void removerPublicacao(Publicacao publicacao) {
        this.publicacaoDao.deletar(publicacao);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A publicação foi deletada com sucesso!"));

    }

    public String alterarPublicacao(Publicacao publicacao) {
        this.publicacaoDao.atualizar(publicacao);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O publicação foi alterada com sucesso!"));
        return "";
    }

    public Publicacao recuperarPublicacao(long id) {
        return this.publicacaoDao.recuperar(id);
    }

    public List<Publicacao> recuperarTodosPublicacao() {
        return this.publicacaoDao.recuperarTodos();
    }
}
