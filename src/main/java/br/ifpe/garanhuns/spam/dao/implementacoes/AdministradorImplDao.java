/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.dao.implementacoes;

import br.ifpe.garanhuns.spam.dao.AdministradorDao;
import br.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.ifpe.garanhuns.spam.modelo.negocio.Administrador;
import java.util.List;

/**
 *
 * @author Ester
 */
public class AdministradorImplDao implements AdministradorDao{

    @Override
    public Administrador recuperarLogin(String login) {
        try {
            return (Administrador) DaoManagerHiber.getInstance().recover("from Administrador where login=" + login);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public void inserir(Administrador a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void atualizar(Administrador a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public void deletar(Administrador a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public Administrador recuperar(long id) {
        try {
            return (Administrador) DaoManagerHiber.getInstance().recover("from Administrador where id=" + id);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Administrador> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Administrador");
    }

    @Override
    public Administrador entrar(String login, String senha) {
        return null;
    }
    
    
}
