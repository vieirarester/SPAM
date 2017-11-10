/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.dao.implementacoes;

import br.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.ifpe.garanhuns.spam.dao.UsuarioDao;
import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import java.util.List;

/**
 *
 * @author Ester
 */
public class UsuarioImplDao implements UsuarioDao {

    @Override
    public Usuario recuperarLogin(String login) {
        try {
            return (Usuario) DaoManagerHiber.getInstance().recover("from Usuario where login=" + login);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public void inserir(Usuario a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void atualizar(Usuario a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public void deletar(Usuario a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public Usuario recuperar(long id) {
        try {
            return (Usuario) DaoManagerHiber.getInstance().recover("from Usuario where id=" + id);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Usuario> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Usuario");
    }

}
