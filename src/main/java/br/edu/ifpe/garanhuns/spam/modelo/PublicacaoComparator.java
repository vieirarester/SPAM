/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.spam.modelo;

import br.edu.ifpe.garanhuns.spam.modelo.negocio.Publicacao;
import java.util.Comparator;


/**
 *
 * @author Ester
 */
public class PublicacaoComparator implements Comparator<Publicacao>{


    @Override
    public int compare(Publicacao p1, Publicacao p2) {
        String id1 = Long.toString(p1.getId());
        String id2 = Long.toString(p2.getId());
        
        return -id1.compareTo(id2);
    }
    
}
