package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.InfoListaContratti;
import bean.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.DipendenteDao;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class SearchAction extends ActionSupport implements SessionAware {

    private String searchDatName;
    private String searchDatSur;
    private String searchDipSur;
    private String searchDipName;
    private String searchMonth;
    private String searchYear;
    private String searchZone;
    private Map<String, Object> sessionAttributes;


    public String execute() {
        DipendenteDao dip = new DipendenteDao();
        ArrayList<InfoListaContratti> d = new ArrayList<InfoListaContratti>();
        
        
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            //User loggedUser = (User) ActionContext.getContext().getSession().get("loggedUser");
            User loggedUser = (User) sessionAttributes.get("loggedUser");

            if (loggedUser.getTipoUtente().equalsIgnoreCase("acli")) {
                d = (ArrayList<InfoListaContratti>) dip.searchDipendenteDatore(searchZone, searchDatSur, searchDatName, searchDipName, searchDipSur, searchMonth, searchYear, null,null);
            } else {
                request.setAttribute("ddl", "true");
                d = (ArrayList<InfoListaContratti>) dip.searchDipendenteDatore(searchZone, loggedUser.getCognome(), loggedUser.getNome(), searchDipName, searchDipSur, searchMonth, searchYear, loggedUser.getEmail(),null);

            }

            if (d.size() == 0) {
                request.setAttribute("Messaggio", "Nessun risultato trovato");
            }
            request.setAttribute("dipendenti", d);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String getSearchDatName() {
        return searchDatName;
    }

    public void setSearchDatName(String searchDatName) {
        this.searchDatName = searchDatName;
    }

    public String getSearchDatSur() {
        return searchDatSur;
    }

    public void setSearchDatSur(String searchDatSur) {
        this.searchDatSur = searchDatSur;
    }

    public String getSearchDipSur() {
        return searchDipSur;
    }

    public void setSearchDipSur(String searchDipSur) {
        this.searchDipSur = searchDipSur;
    }

    public String getSearchDipName() {
        return searchDipName;
    }

    public void setSearchDipName(String searchDipName) {
        this.searchDipName = searchDipName;
    }

    public String getSearchMonth() {
        return searchMonth;
    }

    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getSearchZone() {
        return searchZone;
    }

    public void setSearchZone(String searchZone) {
        this.searchZone = searchZone;
    }

     @Override
    public void setSession(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    
}
