
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleLocatario")
@ViewScoped
public class ControleLocatario implements Serializable {

    private LocatarioDAO<Locatario> dao;
    private Locatario objeto;
    private PessoaDAO<Pessoa> daoPessoa;

    
    public ControleLocatario(){
        dao = new LocatarioDAO<>();
        daoPessoa = new PessoaDAO<>();
        
    }
    
    public void imprimeLocatario(Integer id){
        objeto = dao.localizar(id);
        List<Locatario> lista = new ArrayList<>();
        lista.add(objeto);
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioLocatarios", parametros, lista);
    }
    
    
    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Locatario();      

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
    

    
    public LocatarioDAO getDao() {
        return dao;
    }

    public void setDao(LocatarioDAO dao) {
        this.dao = dao;
    }

    public Locatario getObjeto() {
        return objeto;
    }

    public void setObjeto(Locatario objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }


    
}
