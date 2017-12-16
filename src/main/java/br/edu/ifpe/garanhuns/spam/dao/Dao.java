/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao;

import java.util.List;

/**
 *
 * @author Ester
 */
public interface Dao <A>{
    
    public void inserir(A a);
    public void deletar(A a);
    public void atualizar(A a);
    public A recuperar(long id);
    public List<A> recuperarTodos();
}
