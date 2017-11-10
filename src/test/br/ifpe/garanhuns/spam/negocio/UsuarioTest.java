/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpe.garanhuns.spam.negocio;

import br.ifpe.garanhuns.spam.modelo.negocio.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20141D12GR0076
 */
public class UsuarioTest {

    public UsuarioTest() {
    }

    @Test
    public void testSenhaCerta() {
        String senhaCerta = "oi";
        String senhaTeste = "oi";

        Usuario usuario = new Usuario();
        usuario.setSenha(senhaCerta);

        assertTrue(usuario.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaErrada() {
        String senhaCerta = "oi";
        String senhaTeste = "oib";

        Usuario usuario = new Usuario();
        usuario.setSenha(senhaCerta);

        assertFalse(usuario.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaVazia() {
        String senhaCerta = "oi";
        String senhaTeste = "";

        Usuario usuario = new Usuario();
        usuario.setSenha(senhaCerta);

        assertFalse(usuario.autenticar(senhaTeste));
    }

    @Test
    public void testSenhaNula() {
        String senhaCerta = "oi";
        String senhaTeste = null;

        Usuario usuario = new Usuario();
        usuario.setSenha(senhaCerta);

        assertFalse(usuario.autenticar(senhaTeste));
    }

}
