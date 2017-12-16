/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.RespostaImplDao;
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
public class RespostaControlador {

    Dao respostaDao = null;

    private Resposta resposta;

    @PostConstruct
    public void inicializar() {
        respostaDao = new RespostaImplDao();
        resposta = new Resposta();
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }
    
    public String inserirResposta(Resposta resposta) {
        
        this.respostaDao.inserir(resposta);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A resposta foi cadastrada com sucesso!"));
        return "";
    }
    
    public void deletarResposta(Resposta resposta) {
        this.respostaDao.deletar(resposta);
    }

    public String atualizarResposta(Resposta resposta) {
        this.respostaDao.atualizar(resposta);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A resposta foi atualizada com sucesso!"));
        return "";
    }

    public Resposta recuperarResposta(long id) {
        return (Resposta) this.respostaDao.recuperar(id);
    }

    public List<Resposta> recuperarTodosResposta() {
        return this.respostaDao.recuperarTodos();
    }
}
