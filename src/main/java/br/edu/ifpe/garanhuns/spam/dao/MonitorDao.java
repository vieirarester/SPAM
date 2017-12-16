/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.dao;

import br.edu.ifpe.garanhuns.spam.modelo.negocio.Monitor;

/**
 *
 * @author João
 */
public interface MonitorDao extends Dao<Monitor> {
        
    public Monitor recuperarLogin(String login);

}
