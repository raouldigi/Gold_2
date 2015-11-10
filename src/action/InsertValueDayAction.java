package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import common.Utility;
import dao.FoglioPresenzeDao;
import bean.FoglioPresenze;
import bean.Giornata;
import com.opensymphony.xwork2.ActionSupport;

public class InsertValueDayAction extends ActionSupport{
	private String accessType;

    public String execute() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();

            HashMap<String, Object> map = (HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request,accessType);

            FoglioPresenze foglio = (FoglioPresenze) map.get("foglioPresenze");

            if (request.getParameter("ferie") != null && request.getParameter("ferie").equalsIgnoreCase("ferie")) {
				// UPDATE FERIE E MALATTIE

                String beginFerie = request.getParameter("ferieDal");
                String endFerie = request.getParameter("ferieAl");
                String beginMal = request.getParameter("malattieDal");
                String endMal = request.getParameter("malattieAl");
                String beginAss = request.getParameter("assenzeDal");
                String endAss = request.getParameter("assenzeAl");

                Integer mese = (Integer) map.get("mese");
                Integer anno = (Integer) map.get("anno");

                FoglioPresenzeDao.updateFerieMalattie(foglio.getIdSituazione_presenze(), beginFerie, endFerie, beginMal, endMal, beginAss, endAss, mese, anno);

            } else { // UPDATE DAY
                String idfogli_presenze = request.getParameter("idfogli_presenze");
                String lav_ord = request.getParameter("lav_ord");
                String lav_straord = request.getParameter("lav_straord");
                String ferie = request.getParameter("ferie");
                String perm_retrib = request.getParameter("perm_retrib");
                String perm_non_retrib = request.getParameter("perm_non_retrib");
                String malattia = request.getParameter("malattia");
                String assenza = request.getParameter("assenza");
                String idcontratto = request.getParameter("idcontratto");
                String idsituazione_presenze = request.getParameter("idsituazione_presenze");

                Giornata giornata = new Giornata(Long.parseLong(idfogli_presenze), lav_ord, lav_straord,
                        ferie, perm_retrib, perm_non_retrib,
                        malattia, assenza, Long.parseLong(idcontratto), Long.parseLong(idsituazione_presenze));

                FoglioPresenzeDao.insertOrUpdateGiornata(giornata);
            }

            //SAVE delle note/osservazioni (potrebbero esser state modificate)
            String noteBusta = request.getParameter("note_busta");
            String osservazioni = request.getParameter("osservazioni_foglio");
            FoglioPresenzeDao.aggiornaNoteFoglio(foglio.getIdSituazione_presenze(), noteBusta, osservazioni);

            map = (HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request, accessType);

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
