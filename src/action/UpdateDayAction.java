package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import common.Utility;
import dao.FoglioPresenzeDao;
import bean.Giornata;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateDayAction extends ActionSupport {

    private String id;
    private String giorno, accessType;

    public String execute() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();

            id = request.getParameter("idfogli_presenze");
            giorno = request.getParameter("giorno");

            HashMap<String, Object> map = (HashMap<String, Object>) Utility.setAttributeForJspFoglioPresenze(request, accessType);

            if (id != null && !id.isEmpty()) {

                Giornata day = FoglioPresenzeDao.getGiornata(Long.parseLong(id));
                if (day != null) {
                    request.setAttribute("day", day);
                    request.setAttribute("dayTableVisibility", "true");
//                    request.setAttribute("giorno", giorno);    Dal momento che ho settato la variabile giorno e ho inserito i getter e setter questa non è necessaria

                    return (String) map.get("return");
                }
            }
            return "error";

        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
}
