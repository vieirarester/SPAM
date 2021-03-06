/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao.implementacoes;

import br.edu.ifpe.garanhuns.spam.dao.DaoManagerHiber;
import br.edu.ifpe.garanhuns.spam.dao.PublicacaoDao;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import java.util.List;

/**
 *
 * @author Ester
 */
public class PublicacaoImplDao implements PublicacaoDao{    

    @Override
    public List<Publicacao> recuperarPorDisciplina(Disciplina disciplina) {
        return DaoManagerHiber.getInstance().recover("from Publicacao where disciplina="+disciplina);
    }
    
    @Override
    public void inserir(Publicacao a) {
        DaoManagerHiber.getInstance().persist(a);
    }

    @Override
    public void deletar(Publicacao a) {
        DaoManagerHiber.getInstance().delete(a);
    }

    @Override
    public void atualizar(Publicacao a) {
        DaoManagerHiber.getInstance().update(a);
    }

    @Override
    public Publicacao recuperar(long id) {
        try {
            return (Publicacao) DaoManagerHiber.getInstance().recover("from Publicacao where id=" + id);
        } catch (IndexOutOfBoundsException excecao) {
            return null;
        }
    }

    @Override
    public List<Publicacao> recuperarTodos() {
        return DaoManagerHiber.getInstance().recover("from Publicacao");
    }   
    
}
