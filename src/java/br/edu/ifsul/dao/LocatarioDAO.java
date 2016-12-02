
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Locatario;

import java.io.Serializable;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class LocatarioDAO<T> extends DAOGenerico<Locatario> implements Serializable {

    public LocatarioDAO(){
        super();
        super.setClassePersistente(Locatario.class);
        super.setOrdem("nome");
    }
    
}
