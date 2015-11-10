package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import common.Utility;
import dao.FoglioPresenzeDao;
import bean.FoglioPresenze;
import com.opensymphony.xwork2.ActionSupport;

public class AggiornaStatoFoglioPresenzeAction extends ActionSupport {

    private String stato, accessType;

    public String execute() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();

            stato = request.getParameter("stato");

            HashMap<String, Object> map = (HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request, accessType);

            FoglioPresenze foglio = (FoglioPresenze) map.get("foglioPresenze");
            String contratto = (String) map.get("contratto");
            Integer anno = (Integer) map.get("anno");
            Integer mese = (Integer) map.get("mese");

            FoglioPresenzeDao.aggiornaStatoFoglio(foglio.getIdSituazione_presenze(), stato);

            if (stato.equalsIgnoreCase("inviato")) {
                FoglioPresenzeDao.aggiornaDataConfermaDDL(foglio.getIdSituazione_presenze());
            }

            // Dopo aver aggiornato le note ricarico il foglio aggiornato in request
            foglio = FoglioPresenzeDao.getFoglioPresenze(Integer.parseInt(contratto), anno, mese);
            request.setAttribute("foglioPresenze", foglio);

            return (String) map.get("return");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

}
