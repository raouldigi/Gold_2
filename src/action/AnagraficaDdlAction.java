package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import bean.DatoreDiLavoro;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.AnagraficheDao;
import dao.DipendenteDao;

public class AnagraficaDdlAction extends ActionSupport {

    private Integer idanagrafica_ddl;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String zona;
    private String telefono;
    private String cellulare;
    private String email;
    private String userid;
    private String password;
    private String tipo_utente;

    @Override
    public void validate() {
        if ((nome != null) && (nome.trim().equalsIgnoreCase(""))) {
            addFieldError("nome", "* Inserisci il nome");
        }
        if ((cognome != null) && (cognome.trim().equalsIgnoreCase(""))) {
            addFieldError("cognome", "* Inserisci il cognome");
        }
//        if ((indirizzo != null) && (indirizzo.trim().equalsIgnoreCase(""))) {
//            addFieldError("indirizzo", "* Inserisci l'indirizzo");
//        }
        if ((zona != null) && (zona.trim().equalsIgnoreCase(""))) {
            addFieldError("zona", "* Inserisci la zona");
        }
        if ((email != null) && (email.trim().equalsIgnoreCase(""))) {
            addFieldError("email", "* Inserisci l' email");
        }
        if ((userid != null) && (userid.trim().equalsIgnoreCase(""))) {
            addFieldError("userid", "* Inserisci username");
        }
        if ((password != null) && (password.trim().equalsIgnoreCase(""))) {
            addFieldError("password", "* Inserisci la password");
        }
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            DipendenteDao dip = new DipendenteDao();
            ArrayList<DatoreDiLavoro> d = new ArrayList<DatoreDiLavoro>();
            d = (ArrayList<DatoreDiLavoro>) dip.getDatoreList();
            request.setAttribute("datori", d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String execute() {
        try {
            DatoreDiLavoro dl = new DatoreDiLavoro();
            dl.setNome(nome.trim());
            dl.setCognome(cognome.trim());
            dl.setIndirizzo(indirizzo.trim());
            dl.setZona(zona.trim());
            dl.setTelefono(telefono.trim());
            dl.setCellulare(cellulare.trim());
            dl.setEmail(email.trim());
            dl.setUserid(userid.trim());
            dl.setTipo_utente(tipo_utente);
            dl.setPassword(password.trim());
            
            if (!AnagraficheDao.addDatoreLavoro(dl)) {
                return SUCCESS; // RIGA MODIFICATA
            }
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String editDatore() {
        return SUCCESS;
    }

    public String okEditDatore() throws Exception {
        try {
            DatoreDiLavoro dl = new DatoreDiLavoro(idanagrafica_ddl, nome, cognome, indirizzo, zona, telefono, cellulare, userid, password, email, tipo_utente);
            if (!AnagraficheDao.updateDatoreLavoro(dl)) {
                return SUCCESS; // RIGA MODIFICATA
            }
            return ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
//
//    public String cancelEditDatore() throws Exception {
//        return "cancel";
//    }
//
    public Integer getIdanagrafica_ddl() {
        return idanagrafica_ddl;
    }

    public void setIdanagrafica_ddl(Integer idanagrafica_ddl) {
        this.idanagrafica_ddl = idanagrafica_ddl;
    }

    public String getTipo_utente() {
        return tipo_utente;
    }

    public void setTipo_utente(String tipo_utente) {
        this.tipo_utente = tipo_utente;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
