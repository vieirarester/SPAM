/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao.implementacoes;

import br.edu.ifpe.garanhuns.spam.dao.Dao;
import br.edu.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Resposta;
import java.util.List;

/**
 *
 * @author Ester
 */
public class RespostaImplDao implements Dao<Resposta>{

    @Override
    public void inserir(Resposta a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void deletar(Resposta a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public void atualizar(Resposta a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public Resposta recuperar(long id) {
        try{
            return (Resposta) DaoManagerHiber.getInstance().recover("from Resposta where id="+id);
        } catch(IndexOutOfBoundsException excecao){
            return null;
        }
    }

    @Override
    public List<Resposta> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Resposta");
    }
    
}
