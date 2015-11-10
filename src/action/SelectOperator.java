package action;

import java.util.ArrayList;
import java.util.Iterator;

import com.opensymphony.xwork2.ActionContext;

import bean.Dipendente;
import bean.InfoListaContratti;
import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import dao.DipendenteDao;
import java.util.Calendar;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

//
//static class Elenco{
//int contratto;
//String nome;
//String cognome;
//
//
//}
//        
        
public class SelectOperator extends ActionSupport implements SessionAware {

    private String contratto;
    private ArrayList<String> operatorList;
    private ArrayList<String> nomedipeList;

    private String anno;
    private String mese;
//    private Integer annoCorr;
    private String accessType = "ddl";
    private Map<String, Object> sessionAttributes;
    private ArrayList<InfoListaContratti> dipendenti;

    public String execute() {

// HttpServletRequest request = ServletActionContext.getRequest()
        
       
        try {
            
            DipendenteDao dip = new DipendenteDao();
            
            if (contratto == null || contratto.trim().length() == 0) {
                operatorList = new ArrayList<String>();
                nomedipeList = new ArrayList<String>();

                //User loggedUser = (User) ActionContext.getContext().getSession().get("loggedUser");
                User loggedUser = (User) sessionAttributes.get("loggedUser");

                boolean acli = false;
                if (loggedUser.getTipoUtente().equalsIgnoreCase("acli")) {
                    acli = true;
                    setAccessType("acli");
                }

                ArrayList<Dipendente> list = (ArrayList<Dipendente>) DipendenteDao.getDipendenteContrattoList(acli, loggedUser.getEmail());
                for (Iterator<Dipendente> iterator = list.iterator(); iterator.hasNext();) {
                    Dipendente dipendente = (Dipendente) iterator.next();

                    operatorList.add("" + dipendente.getIdcontratto());
                    nomedipeList.add("" + dipendente.getIdcontratto() + " - " + dipendente.getCognome() + " " + dipendente.getNome());
                }
//                annoCorr = Calendar.getInstance().get(Calendar.YEAR);
                dipendenti = (ArrayList<InfoListaContratti>) dip.getLavorazioneSudo(null,null,null,null,null,null,null,null,null);
                return "success";
            }
            return "error";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public String getMese() {
        return mese;
    }

    public void setMese(String mese) {
        this.mese = mese;
    }

    public String getContratto() {
        return contratto;
    }

    public void setContratto(String contratto) {
        this.contratto = contratto;
    }

    public ArrayList<String> getOperatorList() {
        return operatorList;
    }

    public void setOperatorList(ArrayList<String> operatorList) {
        this.operatorList = operatorList;
    }
   
    public ArrayList<String> getNomedipeList() {
        return nomedipeList;
    }

    public void setNomedipeList(ArrayList<String> nomedipeList) {
        this.nomedipeList = nomedipeList;
    }
    
    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

public ArrayList<InfoListaContratti> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(ArrayList<InfoListaContratti> dipendenti) {
        this.dipendenti = dipendenti;
    }
    
    @Override
    public void setSession(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
}
