package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.Dipendente;
import bean.Giornata;
import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import common.Utility;
import dao.DipendenteDao;
import dao.FoglioPresenzeDao;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class FoglioPresenzeAction extends ActionSupport implements SessionAware {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ATTRIBUTI PER SELECT IN loginsuccess.jsp
    private String contratto;
    private ArrayList<String> operatorList;
    private String anno;
    private String mese;
    private Map<String, Object> sessionAttributes;
    private String accessType, email;

    public String execute() {
        boolean acli = false;

    	//System.out.println(accessType);
    	try {
            HttpServletRequest request = ServletActionContext.getRequest();
            //User loggedUser = (User) ActionContext.getContext().getSession().get("loggedUser");
            User loggedUser = (User) sessionAttributes.get("loggedUser");
            
            if (email == null || email.equals("")) {
            	this.setEmail(loggedUser.getEmail()); 
            }
        	//System.out.println(email);


            String contratto = request.getParameter("contratto");
            Integer anno = Integer.parseInt(request.getParameter("anno"));
            Integer mese = Integer.parseInt(request.getParameter("mese"));

            LinkedList<Giornata> giornate = (LinkedList<Giornata>) FoglioPresenzeDao.getGiornateFoglioPresenze(Integer.parseInt(contratto), anno, mese);

            if (giornate == null || giornate.isEmpty()) { // SE IL FOGLIO SELEZIONATO NON ESISTE
                if (!request.getParameter("confermato").equalsIgnoreCase("confermato")) { // SE NON E STATA CHIESTA LA CONFERMA DI CREAZIONE DEL NUOVO FOGLIO 

                    request.setAttribute("confirm_message", "confirm_message"); // occorre per attivare nella jsp il div del popup

                    // Alimento le select della jsp
                    operatorList = new ArrayList<String>();

                    if (accessType.equals("acli")) {
                        acli = true;
                    }

                    ArrayList<Dipendente> list = (ArrayList<Dipendente>) DipendenteDao.getDipendenteContrattoList(acli, email);
                    for (Iterator<Dipendente> iterator = list.iterator(); iterator.hasNext();) {
                        Dipendente dipendente = (Dipendente) iterator.next();

                        operatorList.add("" + dipendente.getIdcontratto());
                    }
                    return "confirm"; // torno nella stessa jsp (loginsuccess.jsp) per aprire il popup di conferma

                } else { // SE IL FOGLIO SELEZIONATO NON ESISTE

                    // CREAZIONE DEL NUOVO FOGLIO PRESENZE
                    Calendar date = Calendar.getInstance();
                    date.set(Calendar.YEAR, anno);
                    date.set(Calendar.MONTH, mese - 1);
                    int lastDayOfMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);

                    Long idSituazionePresenze = FoglioPresenzeDao.insertSituazionePresenze(Long.parseLong(contratto), mese, anno);

                    if (idSituazionePresenze != null && contratto != null) {
                        Giornata giornata = null;
                        for (int i = 0; i < lastDayOfMonth; i++) {
                            date = Calendar.getInstance();
                            date.set(Calendar.YEAR, anno);
                            date.set(Calendar.MONTH, mese - 1);
                            date.set(Calendar.DAY_OF_MONTH, i + 1);
                            Date dataPresenza = date.getTime();
                            giornata = new Giornata(null, "", "", "", "", "", "", "", Long.parseLong(contratto), idSituazionePresenze, dataPresenza);
                            FoglioPresenzeDao.addGiornata(giornata);
                        }
                    }
                }

            }

            return (String) ((HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request,accessType)).get("return");

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

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
