
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Condominio;
import java.io.Serializable;


public class CondominioDAO<T> extends DAOGenerico<Condominio> implements Serializable {

    public CondominioDAO(){
        super();
        super.setClassePersistente(Condominio.class);
    }
    
}
