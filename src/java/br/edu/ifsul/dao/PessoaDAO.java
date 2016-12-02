
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;


public class PessoaDAO<T> extends DAOGenerico<Pessoa> implements Serializable {

    public PessoaDAO(){
        super();
        super.setClassePersistente(Pessoa.class);
        super.setOrdem("nome");
    }
    
}
