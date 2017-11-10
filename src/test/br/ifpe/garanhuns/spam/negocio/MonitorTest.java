/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.negocio;

import br.ifpe.garanhuns.spam.modelo.negocio.Monitor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20141D120122
 */
public class MonitorTest {

    public MonitorTest() {
    }

    @Test
    public void testPasswordRight() {
        String senhaCerta = "oi";
        String senhaTeste = "oi";

        // preparando
        Monitor j = new Monitor();
        j.setSenha(senhaCerta);

        // teste
        assertTrue(j.autenticar(senhaTeste));
    }

    @Test
    public void testPasswordWrong() {
        String senhaCerta = "oi";
        String senhaTeste = "oib";

        // preparando
        Monitor j = new Monitor();
        j.setSenha(senhaCerta);

        // teste
        assertFalse(j.autenticar(senhaTeste));
    }

    @Test
    public void testPasswordVoid() {
        String senhaCerta = "oi";
        String senhaTeste = "";

        // preparando
        Monitor j = new Monitor();
        j.setSenha(senhaCerta);

        // teste
        assertFalse(j.autenticar(senhaTeste));
    }

    @Test
    public void testPasswordNull() {
        String senhaCerta = "oi";
        String senhaTeste = null;

        // preparando
        Monitor j = new Monitor();
        j.setSenha(senhaCerta);

        // teste
        assertFalse(j.autenticar(senhaTeste));
    }

}
