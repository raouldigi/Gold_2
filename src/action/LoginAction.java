package action;

import dao.DipendenteDao;
import dao.LoginDao;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import bean.InfoListaContratti;
import bean.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.SQLException;

public class LoginAction extends ActionSupport implements SessionAware, ModelDriven<User> {

    private static final long serialVersionUID = 1L;
    private String username, userpass;
    private User user;

    //SessionMap<String, String> sessionmap;
    private Map<String, Object> sessionAttributes = null;

    public String execute() {
        try {

            HttpServletRequest request = ServletActionContext.getRequest();

            if (request.getParameter("logout") != null && request.getParameter("logout").equalsIgnoreCase("true")) {
                logout();
                return "login";
            }

            user = LoginDao.validate(username, userpass);

            if (user != null) {
                //ActionContext.getContext().getSession().put("loggedUser", user);
                sessionAttributes.put("loggedUser", user);
                getList(user);

                return "success";
            } else {
                return "error";
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }
    }

    public void getList(User loggedUser) throws Exception {

        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<InfoListaContratti> d = new ArrayList<InfoListaContratti>();

            if (loggedUser.getTipoUtente().equalsIgnoreCase("acli")) {
                d = (ArrayList<InfoListaContratti>) dip.getDipendenteDatore(null);
            } else {
                request.setAttribute("ddl", "true");
                d = (ArrayList<InfoListaContratti>) dip.searchDipendenteDatore(null, loggedUser.getCognome(), loggedUser.getNome(), null, null, null, null, loggedUser.getEmail(), null);
            }

            request.setAttribute("dipendenti", d);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

   
    
//    public void setSession(Map map) {
//        sessionmap = (SessionMap) map;
//        sessionmap.put("login", "true");
//    }   
    @Override
    public void setSession(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public User getModel() {
        return user;
    }

    public void logout() {
        sessionAttributes.remove("loggedUser");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

}
