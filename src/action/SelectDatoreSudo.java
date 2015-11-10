package action;

import java.util.ArrayList;
import java.util.Iterator;

import bean.Dipendente;
import bean.InfoListaContratti;
import com.opensymphony.xwork2.ActionSupport;
import dao.DipendenteDao;
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
public class SelectDatoreSudo extends ActionSupport implements SessionAware {

    private String contratto;
    private ArrayList<String> operatorList;
    private ArrayList<String> nomedipeList;

    private String anno;
    private String mese;
    private String userid;
    private String email;
    private String accessType = "ddl";
    private String cognome;
    private ArrayList<InfoListaContratti> dipendenti;

    @Override
    public String execute() {

        try {

            DipendenteDao dip = new DipendenteDao();
//            ArrayList<InfoListaContratti> d = new ArrayList<InfoListaContratti>();

            if (contratto == null || contratto.trim().length() == 0) {
                operatorList = new ArrayList<String>();
                nomedipeList = new ArrayList<String>();

                ArrayList<Dipendente> list = (ArrayList<Dipendente>) DipendenteDao.getDipendenteContrattoList(false, getEmail());
                for (Iterator<Dipendente> iterator = list.iterator(); iterator.hasNext();) {
                    Dipendente dipendente = (Dipendente) iterator.next();

                    operatorList.add("" + dipendente.getIdcontratto());
                    nomedipeList.add("" + dipendente.getIdcontratto() + " - " + dipendente.getCognome() + " "
                            + dipendente.getNome());
                }

                dipendenti = (ArrayList<InfoListaContratti>) dip.getLavorazioneSudo(null, null, null, null, null, null, null, email, null);
//                dipendenti = (ArrayList<InfoListaContratti>) dip.getDipendenteDatore(email);
                
                
                return SUCCESS;
            }
            return "error";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }


    public ArrayList<InfoListaContratti> getDipendenti() {
        return dipendenti;
    }

    public void setDipendenti(ArrayList<InfoListaContratti> dipendenti) {
        this.dipendenti = dipendenti;
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

    @Override
    public void setSession(Map<String, Object> sessionAttributes) {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

}
