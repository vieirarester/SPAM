/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.dao;

import br.ifpe.garanhuns.spam.modelo.negocio.Administrador;

/**
 *
 * @author Ester
 */
public interface AdministradorDao extends Dao<Administrador>{
    
    public Administrador recuperarLogin(String login);
}
