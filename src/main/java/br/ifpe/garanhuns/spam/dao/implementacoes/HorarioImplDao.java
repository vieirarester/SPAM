/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.dao.implementacoes;

import br.ifpe.garanhuns.spam.dao.Dao;
import br.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.ifpe.garanhuns.spam.modelo.negocio.Horario;
import java.util.List;

/**
 *
 * @author Ester
 */
public class HorarioImplDao implements Dao<Horario>{

    @Override
    public void inserir(Horario a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void deletar(Horario a) {
     DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public void atualizar(Horario a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public Horario recuperar(long id) {
        try {
            return (Horario) DaoManagerHiber.getInstance().recover("from Horario where id=" + id);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Horario> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Horario");
    }
    
}
