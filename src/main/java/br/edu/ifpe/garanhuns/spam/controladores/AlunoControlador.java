/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.AlunoImplDao;
import br.edu.ifpe.garanhuns.spam.dao.implementacoes.MonitorImplDao;
import br.edu.ifpe.garanhuns.spam.modelo.Criptografia;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Usuario;
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
    MonitorDao monitorDao = null;

    private Usuario usuario;
    private Aluno aluno;

    @PostConstruct
    public void inicializar() {
        alunoDao = new AlunoImplDao();
        monitorDao = new MonitorImplDao();
        aluno = new Aluno();
        usuario = new Usuario();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean validarUsuario(String login) {
        boolean jaExiste;
        jaExiste = alunoDao.recuperarLogin(login) != null || monitorDao.recuperarLogin(login)!=null ||  
                login.equals("adm");
        return jaExiste;
    }

    public String inserirAluno(Aluno aluno) {
        
        if (!validarUsuario(aluno.getUsuario().getLogin())) {
            String senhaCripto = Criptografia.criptografar(aluno.getUsuario().getSenha());
            aluno.getUsuario().setSenha(senhaCripto);
            
            this.alunoDao.inserir(aluno);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O aluno foi cadastrado com sucesso!"));
        } else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Este usuário já existe!"));
        }
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
