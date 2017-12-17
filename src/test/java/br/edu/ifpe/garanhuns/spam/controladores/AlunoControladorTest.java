/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.controladores;

import br.edu.ifpe.garanhuns.spam.dao.AlunoDao;
import br.edu.ifpe.garanhuns.spam.dao.MonitorDao;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author João Witor
 */
public class AlunoControladorTest {

    public AlunoControladorTest() {
    }

    @Test (expected=NullPointerException.class)
    public void testeValidarUsuarioQuandoNulo() {
        String login = "";
        AlunoControlador controlador = new AlunoControlador();
        controlador.validarUsuario(login);
    }

    
    
    
}
