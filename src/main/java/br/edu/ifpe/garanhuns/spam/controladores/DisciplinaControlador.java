/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.DisciplinaImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
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
public class DisciplinaControlador {

    Dao disciplinaDao = null;

    private Disciplina disciplina;

    @PostConstruct
    public void inicializar() {
        disciplinaDao = new DisciplinaImplDao();
        disciplina = new Disciplina();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String inserirDisciplina(Disciplina disciplina) {
        this.disciplinaDao.inserir(disciplina);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A disciplina foi cadastrada com sucesso!"));

        return "";
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinaDao.deletar(disciplina);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A disciplina foi deletada com sucesso!"));

    }

    public String alterarDisciplina(Disciplina disciplina) {
        this.disciplinaDao.atualizar(disciplina);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A disciplina foi alterada com sucesso!"));
        return "";
    }

    public Disciplina recuperarDisciplina(long id) {
        return (Disciplina) this.disciplinaDao.recuperar(id);
    }

    public List<Disciplina> recuperarTodosDisciplina() {
        return this.disciplinaDao.recuperarTodos();
    }
}
