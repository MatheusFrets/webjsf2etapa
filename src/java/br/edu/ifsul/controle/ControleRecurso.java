
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleRecurso")
@ViewScoped
public class ControleRecurso implements Serializable {

    private RecursoDAO<Recurso> dao;
    private Recurso objeto;
   
    
    public ControleRecurso(){
        dao = new RecursoDAO<>();
        
    }
    
    public void imprimirRelatorio(){
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioRecursosjavaBeans",
                    parametros, dao.getListaTodos());

}
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Recurso();        
    }
    
    public void salvar(){
        boolean persistiu;
        if (objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            UtilMensagens.mensagemInformacao(dao.getMensagem());            
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());            
        }        
    }   
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);            
        } catch (Exception e){
            UtilMensagens.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remover(objeto)){
            UtilMensagens.mensagemInformacao(dao.getMensagem());
        } else {
            UtilMensagens.mensagemErro(dao.getMensagem());
        }
    }

    public RecursoDAO getDao() {
        return dao;
    }

    public void setDao(RecursoDAO dao) {
        this.dao = dao;
    }

    public Recurso getObjeto() {
        return objeto;
    }

    public void setObjeto(Recurso objeto) {
        this.objeto = objeto;
    }

    
    
}
