/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao;

import br.edu.ifpe.garanhuns.spam.modelo.negocio.Disciplina;
import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import java.util.List;

/**
 *
 * @author Ester
 */
public interface PublicacaoDao extends Dao<Publicacao>{
    
    public List<Publicacao> recuperarPorDisciplina(Disciplina disciplina);
    
}
