package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.Dipendente;
import bean.FoglioPresenze;
import bean.Giornata;
import bean.User;
import dao.DipendenteDao;
import dao.FoglioPresenzeDao;

//import org.apache.log4j.Logger;
public class Utility extends ActionSupport {

    private static final long serialVersionUID = 1L;

    public static Map<String, Object> setAttributeForJspFoglioPresenze(HttpServletRequest request, String accessType) {
//		File newFile = null;
        String returnType;
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (accessType == null) {
            if (((User) ActionContext.getContext().getSession().get("loggedUser")).getTipoUtente().equalsIgnoreCase("acli")) {
                returnType = "acli";
            } else {
                returnType = "ddl";
            }
        } else {
            returnType = accessType;
        }

        try {
            String contratto = request.getParameter("contratto");
            Integer anno = Integer.parseInt(request.getParameter("anno"));
            Integer mese = Integer.parseInt(request.getParameter("mese"));

            if (contratto != null && contratto.trim().length() != 0) {

                FoglioPresenze foglio = FoglioPresenzeDao.getFoglioPresenze(Integer.parseInt(contratto), anno, mese);

                LinkedList<Giornata> giornate = (LinkedList<Giornata>) FoglioPresenzeDao.getGiornateFoglioPresenze(Integer.parseInt(contratto), anno, mese);

                Dipendente dipendente = null;
                ArrayList<Dipendente> results = (ArrayList<Dipendente>) DipendenteDao.getDipendenteInfo(contratto);

                if (results != null && results.size() > 0) {
                    dipendente = results.get(0);
                }

                //FESTIVITA
                ArrayList<String> festivita = (ArrayList<String>) FoglioPresenzeDao.getFestivitaByContratto(Long.parseLong(contratto));
                ArrayList<String> festivitaNaz = (ArrayList<String>) FoglioPresenzeDao.getFestivitaNazionali();

                request.setAttribute("festivitaNaz", festivitaNaz);
                request.setAttribute("festivita", festivita);
                request.setAttribute("dipendente", dipendente);
                request.setAttribute("foglioPresenze", foglio);
                request.setAttribute("contratto", contratto);
                request.setAttribute("anno", anno);
                request.setAttribute("mese", mese);
                request.setAttribute("giornate", giornate);

                // FILL RETURN MAP
                map.put("festivitaNaz", festivitaNaz);
                map.put("festivita", festivita);
                map.put("foglioPresenze", foglio);
                map.put("contratto", contratto);
                map.put("anno", anno);
                map.put("mese", mese);
                map.put("giornate", giornate);
                map.put("dipendente", dipendente);
                map.put("return", returnType);
                return map;
            }

            map.put("return", "error");
            return map;

        } catch (Exception e) {
//			LOG.error("Error while renaming file", e);
            e.printStackTrace();
            map.put("return", "error");
            return map;
        }
    }

   
}
