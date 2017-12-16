/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao.implementacoes;

import br.edu.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import java.util.List;

/**
 *
 * @author João
 */
public class MonitorImplDao implements MonitorDao {

    @Override
    public Monitor recuperarLogin(String login) {
        try {
            DaoManagerHiber dmh = DaoManagerHiber.getInstance();
            
            List<Monitor> monitores = dmh.recover("from Monitor where login = '" + login+"'");
            
            return (Monitor) monitores.get(0);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public void inserir(Monitor a) {
        try{
            DaoManagerHiber.getInstance().persist(a);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Monitor a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public void deletar(Monitor a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public Monitor recuperar(long id) {
        try {
            return (Monitor) DaoManagerHiber.getInstance().recover("from Monitor where id=" + id);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Monitor> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Monitor");
    }

}
