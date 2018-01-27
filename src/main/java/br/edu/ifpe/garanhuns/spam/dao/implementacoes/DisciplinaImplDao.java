/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao.implementacoes;

import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import java.util.List;

/**
 *
 * @author Ester
 */
public class DisciplinaImplDao implements Dao<Disciplina>{
    
    @Override
    public void inserir(Disciplina a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void deletar(Disciplina a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public void atualizar(Disciplina a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public Disciplina recuperar(long id) {
        try{
            return (Disciplina) DaoManagerHiber.getInstance().recover("from Disciplina where id="+id).get(0);
        }catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Disciplina> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Disciplina");
    }
    
}
