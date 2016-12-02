
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.UnidadeCondominial;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilMensagens;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "controleCondominio")
@ViewScoped
public class ControleCondominio implements Serializable {

    private CondominioDAO<Condominio> dao;
    private Condominio objeto;
    private UnidadeCondominial unidade;
    private Boolean novoUnidade;
   
    
    public ControleCondominio(){
        dao = new CondominioDAO<>();
        
    }
    
    public void imprimirRelatorio(){
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioCondominiosjavaBeans",
                    parametros, dao.getListaTodos());

}
    
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Condominio();        
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
    
    public void novoUnidade(){
        unidade = new UnidadeCondominial();
        novoUnidade = true;
    }
    
    public void alterarUnidade(int index){
        unidade = objeto.getUnidades().get(index);
        novoUnidade = false;
    }
    
    public void salvarUnidade(){
      
            objeto.adicionarUnidades(unidade);

        UtilMensagens.mensagemInformacao("Item persistido com sucesso!");
    }
    
    public void removerUnidade(int index){
        objeto.removerUnidades(index);
        UtilMensagens.mensagemInformacao("Item removido com sucesso!");
    }

    public CondominioDAO getDao() {
        return dao;
    }

    public void setDao(CondominioDAO dao) {
        this.dao = dao;
    }

    public Condominio getObjeto() {
        return objeto;
    }

    public void setObjeto(Condominio objeto) {
        this.objeto = objeto;
    }

    public UnidadeCondominial getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeCondominial unidade) {
        this.unidade = unidade;
    }

    public Boolean getNovoUnidade() {
        return novoUnidade;
    }

    public void setNovoUnidade(Boolean novoUnidade) {
        this.novoUnidade = novoUnidade;
    }

    
    
}
