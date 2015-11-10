package action;

import bean.DatoreDiLavoro;
import bean.Dipendente;
import bean.InfoListaContratti;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.DipendenteDao;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author gminardi
 */
public class ListeDdlDipeAction extends ActionSupport {

    private String cognomedip;
    private String nomedip;
    private ArrayList<String> sceltaCitta = new ArrayList<String>();

    public String getCognomedip() {
        return cognomedip;
    }

    public void setCognomedip(String cognomedip) {
        this.cognomedip = cognomedip;
    }

    public String getNomedip() {
        return nomedip;
    }

    public void setNomedip(String nomedip) {
        this.nomedip = nomedip;
    }

    
    

    public String execute() {
        return "success";
    }

    public String getTabellaDipendenti() {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<Dipendente> d = new ArrayList<Dipendente>();

            d = (ArrayList<Dipendente>) dip.getDipendenteList();

            request.setAttribute("dipendenti", d);
            return SUCCESS;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getTabellaDatori() {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<DatoreDiLavoro> d = new ArrayList<DatoreDiLavoro>();

            d = (ArrayList<DatoreDiLavoro>) dip.getDatoreList();

            request.setAttribute("datori", d);
            return SUCCESS;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getTabellaDatoriContratti() {

//        int year = Calendar.getInstance().get(Calendar.YEAR);

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<InfoListaContratti> d = new ArrayList<InfoListaContratti>();

            d = (ArrayList<InfoListaContratti>) dip.getListDatoreContratti();
            request.setAttribute("datori", d);

            return SUCCESS;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String getTabellaContratti() {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<InfoListaContratti> c = new ArrayList<InfoListaContratti>();

            c = (ArrayList<InfoListaContratti>) dip.getListContratti();
            request.setAttribute("contratti", c);

            return SUCCESS;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String getTabellaContrattiProfilo() {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<InfoListaContratti> c = new ArrayList<InfoListaContratti>();

            c = (ArrayList<InfoListaContratti>) dip.getListContrattiProfilo();
            request.setAttribute("contratti", c);

            return SUCCESS;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return ERROR;
        }
    }

	public ArrayList<String> getSceltaCitta() {
		return sceltaCitta;
	}

	public void setSceltaCitta(ArrayList<String> sceltaCitta) {
		this.sceltaCitta = sceltaCitta;
	}

}
