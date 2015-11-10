package action;

import bean.InfoListaContratti;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DipendenteDao;
import dao.FoglioPresenzeDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class FoglioRiepilogativoAction extends ActionSupport {

    private String nome;
    private String cognome;
    private int anno;
    private int idcontratto;

    public void validate() {

      if ((String.valueOf(idcontratto) != null) && (String.valueOf(idcontratto).trim().equalsIgnoreCase("0"))) {
            if ((nome != null) && (nome.trim().equalsIgnoreCase(""))) {
                addFieldError("nome", "* Inserisci il nome del datore");
            }
            if ((cognome != null) && (cognome.trim().equalsIgnoreCase(""))) {
                addFieldError("cognome", "* Inserisci il cognome del datore");
            }
            
        }
        if (((nome != null) && nome.trim().equalsIgnoreCase("")) && ((cognome != null) && cognome.trim().equalsIgnoreCase("0"))) {
            if ((String.valueOf(idcontratto) != null) && (String.valueOf(idcontratto).trim().equalsIgnoreCase(""))) {
                addFieldError("idcontratto", "* Inserisci il numero di contratto");
            }
        }
		

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<InfoListaContratti> d = new ArrayList<InfoListaContratti>();

            d = (ArrayList<InfoListaContratti>) dip.getListDatoreContratti();
            request.setAttribute("datori", d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String execute() {

        HttpServletRequest request = ServletActionContext.getRequest();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        Integer idcontratto = Integer.parseInt(request.getParameter("idcontratto"));
        Integer anno = Integer.parseInt(request.getParameter("anno"));

        FoglioPresenzeDao foglio = new FoglioPresenzeDao();
        ArrayList<InfoListaContratti> r = new ArrayList<InfoListaContratti>();
        try {
            r = (ArrayList<InfoListaContratti>) foglio.getFoglioRiepilogativo(nome, cognome, idcontratto, anno);
            request.setAttribute("foglioR", r);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getIdcontratto() {
        return idcontratto;
    }

    public void setIdcontratto(int idcontratto) {
        this.idcontratto = idcontratto;
    }

}
