/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao.implementacoes;

import br.edu.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Aluno;
import java.util.List;
import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;

/**
 *
 * @author Ester
 */
public class AlunoImplDao implements AlunoDao {

    @Override
    public Aluno recuperarLogin(String login) {
        try {
            DaoManagerHiber dmh = DaoManagerHiber.getInstance();

            List<Aluno> alunos = dmh.recover("from Aluno where login = '" + login+"'");
            
            return (Aluno) alunos.get(0);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public void inserir(Aluno a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void atualizar(Aluno a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public void deletar(Aluno a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public Aluno recuperar(long id) {
        try {
            return (Aluno) DaoManagerHiber.getInstance().recover("from Aluno where id=" + id).get(0);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Aluno> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Aluno");
    }

}
