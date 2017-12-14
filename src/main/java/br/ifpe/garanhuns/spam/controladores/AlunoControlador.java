/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.controladores;

import br.ifpe.garanhuns.spam.dao.AlunoDao;
import br.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Aluno;
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
public class AlunoControlador {

    AlunoDao alunoDao = null;
    
    private Usuario usuario;
    private Aluno aluno;

    @PostConstruct
    public void inicializar() {
        alunoDao = new AlunoImplDao();
        aluno = new Aluno();
        usuario = new Usuario();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String inserirAluno(Aluno aluno) {

        this.alunoDao.inserir(aluno);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O aluno foi cadastrado com sucesso!"));
        return "";
    }

    public void deletarAluno(Aluno aluno) {
        this.alunoDao.deletar(aluno);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O aluno foi deletado com sucesso!"));

    }

    public String atualizarAluno(Aluno aluno) {
        this.alunoDao.atualizar(aluno);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O aluno foi atualizado com sucesso!"));
        return "";
    }

    public Aluno recuperarAluno(long id) {
        return this.alunoDao.recuperar(id);
    }

    public List<Aluno> recuperarTodosAluno() {
        return this.alunoDao.recuperarTodos();
    }
}
